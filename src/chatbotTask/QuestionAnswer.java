package chatbotTask;

import java.util.Collection;
import java.util.HashSet;

public class QuestionAnswer {
	
	private String question;
	private HashSet<String> answers;
	
	public QuestionAnswer(String question, String answer) {
		this.question = question;
		this.answers = new HashSet<String>();
		answers.add(answer);
	}
	
	public QuestionAnswer(String question, Collection<String> answers) {
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
