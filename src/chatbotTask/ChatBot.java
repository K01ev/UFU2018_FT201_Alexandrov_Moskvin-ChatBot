package chatbotTask;

import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;

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
	
	private String[] reaction(String message) {
		String[] answer;
		if (currentQuestion == null)
			changeQuestion();
		switch(message) {
			case "/help":
				answer = new String[] {getHelpStr(), getQuestion()};
				break;
			default:
				answer = new String[] {checkAnswer(message), getQuestion()};
				break;
		}
		return answer;
	}

	public MyMessage[] getHelp() {
		return new MyMessage[] {new MyMessage(getHelpStr())};
	}
	
	public String getHelpStr() {
		return Info.help;
	}



	@Override
	public String getName() {
		return "question_answer_bot";
	}



	@Override
	public MyMessage[] reaction(MyMessage message) {
		if (message.hasText()) {
			String[] strAnswers = reaction(message.getText());
			MyMessage[] answers = new MyMessage[strAnswers.length];
			for (int i = 0; i < answers.length; i++) {
				answers[i] = new MyMessage();
				answers[i].setText(strAnswers[i]);
			}
			return answers;
		}
		return new MyMessage[0];
	}
	
}
