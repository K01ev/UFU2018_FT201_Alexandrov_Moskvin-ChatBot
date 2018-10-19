package chatbotTask;

public class PostBotFactory implements IChatBotFactory {

	@Override
	public IChatBot getNewChatBot() {
		return new PostBot(new DefaultPostAPI());
	}

}
