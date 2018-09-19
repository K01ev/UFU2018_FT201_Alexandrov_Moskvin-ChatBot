package chatbotTask;

import java.util.Collection;
import java.util.HashSet;

public class QuestionAnswer {
	
	private String question;
	private HashSet<String> answers;
	
	public QuestionAnswer(String question, String anwser) {
		this.question = question;
		this.answers = new HashSet<String>();
		answers.add(anwser);
	}
	
	public QuestionAnswer(String question, Collection<String> anwsers) {
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
