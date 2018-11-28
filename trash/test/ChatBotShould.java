package chatbotTask;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import chatbotTask.ChatBot;
import chatbotTask.IQuestionGenerator;
import chatbotTask.Info;
import chatbotTask.QuestionAnswer;
import containers.MyMessage;

class ChatBotShould {

	@Test
	void testCheckCorrectAnswer() {
		ChatBot bot = new ChatBot(new TestQuestionGenerator());
		MyMessage message = new MyMessage();
		message.setText("123");
		MyMessage[] result = bot.reaction(message);
		assertEquals("Correct!", result[0].getText());
	}
	
	@Test
	void testCheckIncorrectAnswer() {
		ChatBot bot = new ChatBot(new TestQuestionGenerator());
		MyMessage message = new MyMessage();
		message.setText("345");
		MyMessage[] result = bot.reaction(message);
		assertEquals("Incorrect!", result[0].getText());
	}
	
	@Test
	void testCheckHelpMessage() {
		ChatBot bot = new ChatBot(new TestQuestionGenerator());
		MyMessage message1 = new MyMessage();
		message1.setText("/help");
		MyMessage[] result = bot.reaction(message1);
		MyMessage message2 = new MyMessage();
		message2.setText("123");
		result = bot.reaction(message2);
		
		result = bot.reaction(message1);
		assertEquals(Info.help, result[0].getText());
		assertEquals("123", result[1].getText());
		
	}

}

class TestQuestionGenerator implements IQuestionGenerator {

	@Override
	public QuestionAnswer getQuestion() {
		return new QuestionAnswer("123", "123");
	}
	
}
