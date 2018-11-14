package chatbotTask;

public class PostAPIFactory {
	
	public PostAPI getNewPostAPI() {
		try {
			return new PostAPI();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
		
	}
}
