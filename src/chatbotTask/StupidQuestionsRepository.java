package chatbotTask;

public class StupidQuestionsRepository implements IQuestionGenerator{
	
	private int index;
	private QuestionAnswer[] questions;
	
	public StupidQuestionsRepository(QuestionAnswer[]questions) {
		index = 0;
		this.questions = questions.clone();
	}
	@Override
	public QuestionAnswer getQuestion() {
		QuestionAnswer question = questions[index];
		index = (index +1) % questions.length;
		return question;
	}
}
