package chatbotTask;

public class ChatBotFactory implements IChatBotFactory {
	
	public ChatBotFactory() {
		
	}
	
	
	@Override
	public IChatBot getNewChatBot() {
		return new ChatBot(new StupidQuestionsRepository(new QuestionAnswer[] {
				new QuestionAnswer("Дважды два?", "4"),
				new QuestionAnswer("Столица Дании?", "Копенгаген")
		}));
	}

}
