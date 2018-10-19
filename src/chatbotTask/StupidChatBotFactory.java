package chatbotTask;

public class StupidChatBotFactory implements IChatBotFactory {
	
	public StupidChatBotFactory() {
		
	}
	
	
	@Override
	public IChatBot getNewChatBot() {
		return new ChatBot(new StupidQuestionsRepository(new QuestionAnswer[] {
				new QuestionAnswer("Дважды два?", "4"),
				new QuestionAnswer("Столица Дании?", "Копенгаген")
		}));
	}

}
