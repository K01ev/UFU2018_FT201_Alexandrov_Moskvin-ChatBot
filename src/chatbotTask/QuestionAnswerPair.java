package chatbotTask;

import java.util.Collection;
import java.util.HashSet;

public class QuestionAnswerPair {
	
	private String question;
	private HashSet<String> answers;
	
	public QuestionAnswerPair(String question, String anwser) {
		this.question = question;
		this.answers = new HashSet<String>();
		answers.add(anwser);
	}
	
	public QuestionAnswerPair(String question, Collection<String> anwsers) {
		this.question = question;
		this.answers = new HashSet<String>(answers);
	}
	
	public String getQuestion() {
		return question;
	}
	
	public boolean isAnswer(String contender) {
		return answers.contains(contender);
	}
	
	private boolean addAnswer(String answer) {
		return answers.add(answer);
	}
}
