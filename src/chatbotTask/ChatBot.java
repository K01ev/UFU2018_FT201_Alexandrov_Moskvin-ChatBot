package chatbotTask;

public class ChatBot //в этом классе можно реализовать все фичи, которые ты хотел сделать в User
//но нам пока что это не нужно, зачем все усложнять? все и так сложно
{
	
	private IQuestionGenerator qGenerator;
	private QuestionAnswer currentQuestion;
	private boolean userAnsweredCorrect; //просто у тебя при наборе help скипался вопрос
								  //может ты найдешь лучшее решение этой проблемы
	public ChatBot(IQuestionGenerator generator) {
		qGenerator = generator;
		userAnsweredCorrect = true;
	}
		
	
	
	public void changeQuestion() {
		 //подстроился под код, написанный в Main
		currentQuestion = qGenerator.getQuestion();
		userAnsweredCorrect = false;
	}
	
	public String checkAnswer(String contender) {

		
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
		switch(message) { //возможно будут другие команды, которые можно юзать прямо во время игры
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
