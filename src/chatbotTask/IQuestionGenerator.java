package chatbotTask;

public interface IQuestionGenerator { // генератор вопросов Хранилище вопросов или их генератор должен
	//быть отделён от логики построения диалога.
	
	public QuestionAnswer getQuestion();
}
