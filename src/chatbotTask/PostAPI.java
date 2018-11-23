package chatbotTask;

import java.util.List;

import javax.xml.bind.JAXBIntrospector;
import javax.xml.soap.MessageFactory;
import javax.xml.soap.SOAPConstants;
import javax.xml.soap.SOAPException;

import org.russianpost.operationhistory.GetOperationHistory;
import org.russianpost.operationhistory.GetOperationHistoryResponse;
import org.russianpost.operationhistory.ObjectFactory;
import org.russianpost.operationhistory.data.AuthorizationHeader;
import org.russianpost.operationhistory.data.OperationHistoryRecord;
import org.russianpost.operationhistory.data.OperationHistoryRequest;
import org.springframework.oxm.jaxb.Jaxb2Marshaller;
import org.springframework.ws.client.core.support.WebServiceGatewaySupport;
import org.springframework.ws.soap.client.SoapFaultClientException;
import org.springframework.ws.soap.client.core.SoapActionCallback;
import org.springframework.ws.soap.saaj.SaajSoapMessageFactory;

public class PostAPI extends WebServiceGatewaySupport implements IPostAPI {
	
	public static final String url = "https://tracking.russianpost.ru/rtm34";
	public static String postLogin = System.getenv("POST_LOGIN");
	public static String postPassword = System.getenv("POST_PASS");
	
	public PostAPI() throws SOAPException {
		MessageFactory msgFactory;
		msgFactory = MessageFactory.newInstance(SOAPConstants.SOAP_1_2_PROTOCOL);
		SaajSoapMessageFactory saajSoapMessageFactory = new SaajSoapMessageFactory(msgFactory);
		setMessageFactory(saajSoapMessageFactory);
	    
	    
		setDefaultUri(url);
		Jaxb2Marshaller marshaller = marshaller();
		setMarshaller(marshaller);
		setUnmarshaller(marshaller);
	}
	
	public GetOperationHistoryResponse getOperationHistory(String trackNumber) {
		GetOperationHistory request = createRequest(trackNumber);
		
		Object rawResp = JAXBIntrospector.getValue(getWebServiceTemplate()
		        .marshalSendAndReceive(
		                url,
		                new ObjectFactory().createGetOperationHistory(request),
		                new SoapActionCallback(
		                	      "https://tracking.russianpost.ru/rtm34/getOperationHistory")));        
		GetOperationHistoryResponse response = (GetOperationHistoryResponse) rawResp;
		return response;
	}
	
	public Jaxb2Marshaller marshaller() {
		Jaxb2Marshaller marshaller = new Jaxb2Marshaller();
		marshaller.setContextPath("org.russianpost.operationhistory");
		return marshaller;
	}
	
	public GetOperationHistory createRequest(String trackNumber) {
		GetOperationHistory request = new GetOperationHistory();
		AuthorizationHeader authHeader = new AuthorizationHeader();
		authHeader.setLogin(postLogin);
		authHeader.setPassword(postPassword);
		request.setAuthorizationHeader(authHeader);
		
		OperationHistoryRequest req = new OperationHistoryRequest();
		req.setBarcode(trackNumber);
		req.setMessageType(0);
		req.setLanguage("RUS");
		request.setOperationHistoryRequest(req);
		return request;
	}
	
	public PostOperation[] historyRecordsToPostOperations(List<OperationHistoryRecord> operations) {
		PostOperation[] result = new PostOperation[operations.size()];
		
		for (int i = 0; i < result.length; i++) {
			OperationHistoryRecord record = operations.get(i);
			String operType = record.getOperationParameters().getOperType().getName();
			String operDate = record.getOperationParameters().getOperDate().toString();
			result[i] = new PostOperation(operType, operDate);
		}
		
		return result;
	}

	@Override
	public PostOperation[] getFullHistory(String trackNumber) {
		try {
		return historyRecordsToPostOperations(
				getOperationHistory(trackNumber).getOperationHistoryData().getHistoryRecord());
		}
		catch (SoapFaultClientException e) {
			System.out.println(e);
			return null;
		}
	}

}
