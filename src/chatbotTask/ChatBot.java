package chatbotTask;

public class ChatBot
{
	
	private IQuestionGenerator qGenerator;
	private QuestionAnswer currentQuestion;
	private boolean userAnsweredCorrect;
	public ChatBot(IQuestionGenerator generator) {
		qGenerator = generator;
		userAnsweredCorrect = false;
		currentQuestion = generator.getQuestion();
	}
		
	
	
	private void changeQuestion() {
		currentQuestion = qGenerator.getQuestion();
		userAnsweredCorrect = false;
	}
	
	private String checkAnswer(String contender) {

		
		if (currentQuestion.isAnswer(contender)) {
			userAnsweredCorrect = true;
			return "Correct!";
		}
		else {
			userAnsweredCorrect = false;
			return "Incorrect!";
		}

	}
	
	private String getQuestion() {
		if (userAnsweredCorrect)
			changeQuestion();
		return currentQuestion.getQuestion();
		
	}
	
	public String[] reaction(String message) {
		String[] answer;
		switch(message) {
			case "/help":
				answer = new String[] {getHelp(), getQuestion()};
				break;
			default:
				answer = new String[] {checkAnswer(message), getQuestion()};
				break;
		}
		return answer;
	}

	public String getHelp() {
		return Info.help;
	}
	
}
