package chatbotTask.tests;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import chatbotTask.ChatBot;
import chatbotTask.IQuestionGenerator;
import chatbotTask.QuestionAnswer;
import chatbotTask.Info;

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
	
	@Test
	void testCheckHelpMessage() {
		ChatBot bot = new ChatBot(new TestQuestionGenerator());
		String[] result = bot.reaction("/help");
		result = bot.reaction("123");
		result = bot.reaction("/help");
		assertEquals(Info.help, result[0]);
		assertEquals("123", result[1]);
		
	}

}

class TestQuestionGenerator implements IQuestionGenerator {

	@Override
	public QuestionAnswer getQuestion() {
		return new QuestionAnswer("123", "123");
	}
	
}
