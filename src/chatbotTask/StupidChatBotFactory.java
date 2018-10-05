package chatbotTask;

public class StupidChatBotFactory implements IChatBotFactory {

	
	private IQuestionGenerator questionRepository;
	
	public StupidChatBotFactory() {
		questionRepository = new StupidQuestionsRepository(
				new QuestionAnswer[] {new QuestionAnswer("Дважды два?", "4"),
				new QuestionAnswer("Столица Дании", "Копенгаген")});
	}
	
	
	@Override
	public IChatBot getNewChatBot() {
		return new ChatBot(questionRepository);
	}

}
