package chatbotTask;

public class PostBotFactory implements IChatBotFactory {
	
	private PostAPIFactory factory;
	
	public PostBotFactory(PostAPIFactory factory) {
		this.factory = factory;
	}

	@Override
	public IChatBot getNewChatBot() {
		return new PostBot(factory.getNewPostAPI());
	}

}
