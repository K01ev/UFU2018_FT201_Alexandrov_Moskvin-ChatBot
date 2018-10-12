package chatbotTask;

public class StupidChatBotFactory implements IChatBotFactory {
	
	public StupidChatBotFactory() {
		
	}
	
	
	@Override
	public IChatBot getNewChatBot() {
		return new PostBot(new DefaultPostAPI());
	}

}
