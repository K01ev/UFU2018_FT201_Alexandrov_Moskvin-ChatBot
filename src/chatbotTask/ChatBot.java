package chatbotTask;

import java.util.List;;

public class ChatBot {
	
	private List<QuestionAnswerPair> questions;
	
	public ChatBot() {
		
	}
		
	
	
	public Pair<Integer, String> getNextQuestion() {
		//TO DO
		return new Pair(0, "");
	}
	
	public boolean checkAnswer(int id, String contender) {
		return questions.get(id).isAnswer(contender);
	}
	
	
}
