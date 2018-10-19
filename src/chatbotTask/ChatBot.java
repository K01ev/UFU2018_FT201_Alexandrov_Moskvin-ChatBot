package chatbotTask;

public class ChatBot implements IChatBot
{
	
	private IQuestionGenerator qGenerator;
	public QuestionAnswer currentQuestion;
	
	public ChatBot(IQuestionGenerator generator) {
		qGenerator = generator;
	}
		
	
	
	private void changeQuestion() {
		currentQuestion = qGenerator.getQuestion();
	}
	
	private String checkAnswer(String contender) {
		if (currentQuestion.isAnswer(contender)) {
			changeQuestion();
			return "Correct!";
		}
		else 
			return "Incorrect!";
	}
	
	private String getQuestion() {
		return currentQuestion.getQuestion();		
	}
	
	public String[] reaction(String message) {
		String[] answer;
		if (currentQuestion == null)
			changeQuestion();
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



	@Override
	public String getName() {
		return "question_answer_bot";
	}
	
}
