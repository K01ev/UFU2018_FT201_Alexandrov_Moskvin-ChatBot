package chatbotTask;

import org.springframework.oxm.jaxb.Jaxb2Marshaller;
import org.springframework.ws.client.core.support.WebServiceGatewaySupport;
import org.springframework.ws.soap.client.core.SoapActionCallback;
import org.springframework.ws.soap.saaj.SaajSoapMessageFactory;

import java.util.List;

import javax.xml.soap.MessageFactory;
import javax.xml.soap.SOAPConstants;
import javax.xml.soap.SOAPException;

import org.russianpost.operationhistory.*;
import org.russianpost.operationhistory.data.*;

public class PostAPI extends WebServiceGatewaySupport implements IPostAPI{
	
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

	@Override
	public String getPackageInfo(String trackNumber) {
		return getLastOperationInfo(getOperationHistory(trackNumber));
	}
	
	public GetOperationHistoryResponse getOperationHistory(String trackNumber) {
		GetOperationHistory request = createRequest(trackNumber);
		
		Object rawResp = getWebServiceTemplate().marshalSendAndReceive(
				request,
				new SoapActionCallback(
						"https://tracking.russianpost.ru/rtm34/getOperationHistory"));
		GetOperationHistoryResponse response = (GetOperationHistoryResponse) rawResp;
		return response;
	}
	
	public String getLastOperationInfo(GetOperationHistoryResponse response) {
		List<OperationHistoryRecord> operations = response.getOperationHistoryData().getHistoryRecords();
		OperationHistoryRecord lastOperation = operations.get(operations.size() - 1);
		String operType = lastOperation.getOperationParameters().getOperType().getName();
		String operDate = lastOperation.getOperationParameters().getOperDate().toString();
		return operType + "\n" + operDate;
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

}
