package chatbotTask;

import javax.xml.soap.SOAPException;

public class PostAPIFactory {
	
	public PostAPI getNewPostAPI() {
		try {
			return new PostAPI();
		} catch (SOAPException e) {
			e.printStackTrace();
		}
		return null;
		
	}
}
