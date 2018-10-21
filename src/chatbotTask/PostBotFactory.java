package chatbotTask;

public class PostBotFactory implements IChatBotFactory {
	
	private IPostAPI api;
	
	public PostBotFactory(IPostAPI api) {
		this.api = api;
	}

	@Override
	public IChatBot getNewChatBot() {
		return new PostBot(api);
	}

}
