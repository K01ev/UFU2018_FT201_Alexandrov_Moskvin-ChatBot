package chatbotTask;

import java.io.StringReader;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.*;
import javax.xml.xpath.*;

import org.w3c.dom.*;
import org.xml.sax.InputSource;

public class XmlParser { //изменю логику класса, когда научусь парсить xml нормально
	private String response;
	private Document xmlDoc;
	private XPath xpath;
	
	public XmlParser(String response) throws Exception {
		this.response = response;
		
		xmlDoc = loadXMLString();
	    XPathFactory xPathfactory = XPathFactory.newInstance();
	    xpath = xPathfactory.newXPath();
	}
	
	
	public Document loadXMLString() throws Exception {
	    DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
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
}
