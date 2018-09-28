package chatbotTask;

import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.*;

import java.util.List;
import java.util.ArrayList;
import java.util.Arrays;

import org.junit.Before;
import org.junit.jupiter.api.Test;

class QuestionAnswerShould {
	String question = "123";
	ArrayList<String> answers = new ArrayList<String>();
	
	@Before
	void setup() {
		answers.add("345");
		answers.add("678");
		answers.add("901");
	}

	@Test
	void testOneAnswer() {
		QuestionAnswer questionAnswer = new QuestionAnswer(question, answers.get(0));
		isQuestion(questionAnswer, question);
		isAnswer(questionAnswer, answers.get(0));
	}
	
	void isQuestion(QuestionAnswer qe,String question) {
		assertEquals(qe.getQuestion(), question);
	}
	
	void isAnswer(QuestionAnswer qe,String answer) {
		assertTrue("Real answer is not in collection" ,qe.isAnswer(answer));
	}

	@Test 
	void testCollectionAnswers() {
		QuestionAnswer questionAnswer = new QuestionAnswer(question, answers);
		isQuestion(questionAnswer, question);
		for (String answer : answers)
			isAnswer(questionAnswer, answer);
	}
	
}
