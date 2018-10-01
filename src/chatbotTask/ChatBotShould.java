package chatbotTask;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class ChatBotShould {

	@Test
	void testCheckCorrectAnswer() {
		ChatBot bot = new ChatBot(new TestQuestionGenerator());
		String[] result = bot.reaction("123");
		assertEquals("Correct!", result[0]);
	}
	
	@Test
	void testCheckIncorrectAnswer() {
		ChatBot bot = new ChatBot(new TestQuestionGenerator());
		String[] result = bot.reaction("345");
		assertEquals("Incorrect!", result[0]);
	}

}

class TestQuestionGenerator implements IQuestionGenerator {

	@Override
	public QuestionAnswer getQuestion() {
		return new QuestionAnswer("123", "123");
	}
	
}
