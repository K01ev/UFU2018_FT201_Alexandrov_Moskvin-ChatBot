package chatbotTask;

import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

public class QuestionAnswerShould {
	private String question = "123";
	private static ArrayList<String> answers = new ArrayList<String>();
	
	@BeforeAll
	public static void setup() {
		answers.add("345");
		answers.add("678");
		answers.add("901");
	}

	@Test
	public void testOneAnswer() {
		QuestionAnswer questionAnswer = new QuestionAnswer(question, answers.get(0));
		isQuestion(questionAnswer, question);
		isAnswer(questionAnswer, answers.get(0));
	}
	
	public void isQuestion(QuestionAnswer qe,String question) {
		assertEquals(qe.getQuestion(), question);
	}
	
	public void isAnswer(QuestionAnswer qe,String answer) {
		assertTrue("Real answer is not in collection" ,qe.isAnswer(answer));
	}

	@Test 
	public void testCollectionAnswers() {
		QuestionAnswer questionAnswer = new QuestionAnswer(question, answers);
		isQuestion(questionAnswer, question);
		for (String answer : answers)
			isAnswer(questionAnswer, answer);
	}
	
}
