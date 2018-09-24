package chatbotTask;

public class QuestionsRepository implements IQuestionGenerator{
	
	private int index;
	private QuestionAnswer[] questions;
	
	public QuestionsRepository() {
		index = 0;
		this.questions = new QuestionAnswer[] {new QuestionAnswer("Дважды два?", "4"),
				new QuestionAnswer("Столица Дании", "Копенгаген")};
	}
	@Override
	public QuestionAnswer getQuestion() {
		QuestionAnswer question = questions[index];
		index = (index +1) % questions.length;
		return question;
	}
}
