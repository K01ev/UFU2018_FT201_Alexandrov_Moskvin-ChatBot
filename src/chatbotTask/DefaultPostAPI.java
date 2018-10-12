package chatbotTask;

import java.io.StringWriter;
import java.util.List;

import javax.xml.soap.*;
import javax.xml.transform.Source;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.stream.StreamResult;

public class DefaultPostAPI implements IPostAPI{

	public static final String url = "https://tracking.russianpost.ru/rtm34";
	
	private SOAPMessage message;
	private SOAPConnection connection;
	
	
	
	@Override
	public String getPackageInfo(String trackNumber) {
		try {
			createMessage();
			fillMessage(trackNumber);
			
			createConnection();
			SOAPMessage resp = sendRequest();
			return parseResponse(resp);
		}
		catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	public void createMessage() throws SOAPException {
		MessageFactory messageFactory = MessageFactory.newInstance("SOAP 1.2 Protocol");
        message = messageFactory.createMessage();
	}
	
	public void fillMessage(String barCode) throws SOAPException {
		SOAPPart soapPart = message.getSOAPPart();
        SOAPEnvelope envelope = soapPart.getEnvelope();
        SOAPBody body = envelope.getBody();
        
        envelope.addNamespaceDeclaration("soap","http://www.w3.org/2003/05/soap-envelope");
        envelope.addNamespaceDeclaration("oper","http://russianpost.org/operationhistory");
        envelope.addNamespaceDeclaration("data","http://russianpost.org/operationhistory/data");
        envelope.addNamespaceDeclaration("soapenv","http://schemas.xmlsoap.org/soap/envelope/");
        
        SOAPElement operElement = body.addChildElement("getOperationHistory", "oper");
        SOAPElement dataElement = operElement.addChildElement("OperationHistoryRequest","data");
        SOAPElement barcode = dataElement.addChildElement("Barcode","data");
        SOAPElement messageType = dataElement.addChildElement("MessageType","data");
        SOAPElement language = dataElement.addChildElement("Language","data");
        SOAPElement dataAuth = operElement.addChildElement("AuthorizationHeader","data");
        
        SOAPFactory sf = SOAPFactory.newInstance();
        Name must = sf.createName("mustUnderstand","soapenv","http://schemas.xmlsoap.org/soap/envelope/");
        dataAuth.addAttribute(must, "1");
        SOAPElement login = dataAuth.addChildElement("login", "data");
        SOAPElement password = dataAuth.addChildElement("password","data");

        barcode.addTextNode(barCode);
        messageType.addTextNode("0");
        language.addTextNode("RUS");
        login.addTextNode("");
        password.addTextNode("");
        
        message.saveChanges();
	}
	
	public void createConnection() throws SOAPException {
		SOAPConnectionFactory soapConnFactory = SOAPConnectionFactory.newInstance();
        connection = soapConnFactory.createConnection();
	}
	
	public SOAPMessage sendRequest() throws Exception {
		SOAPMessage soapResponse = connection.call(message, url);
		closeConnection();
		return soapResponse;
	}
	
	public void closeConnection() throws SOAPException {
		connection.close();
	}
	
	public String parseResponse(SOAPMessage response) throws Exception {
		StringWriter sw = new StringWriter();
		Source sourceContent = response.getSOAPPart().getContent();
        Transformer t= TransformerFactory.newInstance().newTransformer();
        StreamResult result = new StreamResult(sw);
        t.transform(sourceContent, result);
        XmlParser parser = new XmlParser(sw.toString());
        List<String> historyTags = 
        		parser.getTagsInfo
        			("//*[local-name()='historyRecord'][last()]//*[local-name()='OperationParameters']//*[local-name()='OperType']//*[local-name()='Name']",
        			 "//*[local-name()='historyRecord'][last()]//*[local-name()='OperationParameters']//*[local-name()='OperDate']");
        
        return String.join("\n", historyTags);
	}

}
