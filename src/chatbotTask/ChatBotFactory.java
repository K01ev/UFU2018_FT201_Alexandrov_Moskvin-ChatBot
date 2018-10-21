package chatbotTask;

public class ChatBotFactory implements IChatBotFactory {
	
	private IQuestionGenerator generator;
	
	public ChatBotFactory(IQuestionGenerator generator) {
		this.generator = generator;
	}
	
	
	@Override
	public IChatBot getNewChatBot() {
		return new ChatBot(generator);
	}

}
