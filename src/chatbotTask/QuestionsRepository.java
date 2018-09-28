package chatbotTask;

public class QuestionsRepository implements IQuestionGenerator{
	
	private int index;
	private QuestionAnswer[]questions;
	
	public QuestionsRepository(QuestionAnswer[]questions) {
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
