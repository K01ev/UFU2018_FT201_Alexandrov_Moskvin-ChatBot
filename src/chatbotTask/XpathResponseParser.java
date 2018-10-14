package chatbotTask;

import java.io.StringReader;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.xml.namespace.NamespaceContext;
import javax.xml.parsers.*;
import javax.xml.xpath.*;

import org.w3c.dom.*;
import org.xml.sax.InputSource;

public class XpathResponseParser {
	private String response;
	private Document xmlDoc;
	private XPath xpath;
	
	public XpathResponseParser(String response) throws Exception {
		this.response = response;
		
		xmlDoc = loadXMLString();
		
		NamespaceContext ctx = setupNamespaceContext();
	    XPathFactory xPathfactory = XPathFactory.newInstance();
	    xpath = xPathfactory.newXPath();
	    xpath.setNamespaceContext(ctx);
	}
	
	
	public Document loadXMLString() throws Exception {
	    DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
	    dbf.setNamespaceAware(true);
	    DocumentBuilder db = dbf.newDocumentBuilder();
	    InputSource is = new InputSource(new StringReader(response));

	    return db.parse(is);
	}

	public List<String> getFullNameFromXml(String path) throws XPathExpressionException {
	    XPathExpression expr = xpath.compile(path);
	    NodeList nodeList = (NodeList) expr.evaluate(xmlDoc, XPathConstants.NODESET);
	    List<String> ids = new ArrayList<String>(nodeList.getLength());
	    
	    if (nodeList.getLength() == 0) {
	    	return ids;
	    }
	    
	    for(int i=0;i<nodeList.getLength(); i++) {
	        Node x = nodeList.item(i);
	        ids.add(x.getFirstChild().getNodeValue());
	    }
	    
	    return ids;
	}
	
	public List<String> getTagsInfo(String ... paths) throws Exception {
		List<String> tagsInfo = new ArrayList<String>();
		
		for (String path : paths) {
			tagsInfo.addAll(getFullNameFromXml(path));
		}
		
		return tagsInfo;
	}
	
	public NamespaceContext setupNamespaceContext() {
		return new NamespaceContext() {
		    public String getNamespaceURI(String prefix) {
		        switch(prefix) {
		        	case "S":
		        		return "http://www.w3.org/2003/05/soap-envelope";
		        	case "ns2":
		        		return "http://russianpost.org/sms-info/data";
		        	case "ns3":
		        		return "http://russianpost.org/operationhistory/data";
		        	case "ns4":
		        		return "http://schemas.xmlsoap.org/soap/envelope/";
		        	case "ns5":
		        		return "http://www.russianpost.org/custom-duty-info/data";
		        	case "ns6":
		        		return "http://www.russianpost.org/RTM/DataExchangeESPP/Data";
		        	case "ns7":
		        		return "http://russianpost.org/operationhistory";
	        		default:
	        			return null;
		        }
		    }
		    public Iterator getPrefixes(String val) {
		        return null;
		    }
		    public String getPrefix(String uri) {
		        return null;
		    }
		};
	}
}
