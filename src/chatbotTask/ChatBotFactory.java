package chatbotTask;

public class ChatBotFactory implements IChatBotFactory {
	
	@Override
	public IChatBot getNewChatBot() {
		return new ChatBot(new StupidQuestionsRepository(Info.questions));
	}

}
