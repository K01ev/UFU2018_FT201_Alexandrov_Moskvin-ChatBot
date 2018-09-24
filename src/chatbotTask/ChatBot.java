package chatbotTask;

public class ChatBot //в этом классе можно реализовать все фичи, которые ты хотел сделать в User
//но нам пока что это не нужно, зачем все усложнять? все и так сложно
{
	
	private IQuestionGenerator qGenerator;
	private QuestionAnswer currentQuestion;
	private boolean userAnswered; //просто у тебя при наборе help скипался вопрос
								  //может ты найдешь лучшее решение этой проблемы
	public ChatBot(IQuestionGenerator generator) {
		qGenerator = generator;
		userAnswered = true;
	}
		
	
	
	public String getNextQuestion() {
		if (!userAnswered) return null; //подстроился под код, написанный в Main
		currentQuestion = qGenerator.getQuestion();
		userAnswered = false;
		return currentQuestion.getQuestion();
	}
	
	public String checkAnswer(String contender) {
		if (contender.equals("/help")) //возможно будут другие команды, которые можно юзать прямо во время игры
			return getHelp();
		userAnswered = true;
		return (currentQuestion.isAnswer(contender)) ? "Correct!" : "Incorrect!";
	}
	
	

	public String getHelp() {
		return Info.help;
	}
	
}
