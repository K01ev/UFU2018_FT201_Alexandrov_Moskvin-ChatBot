package chatbotTask;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class PostBotShould {

	@Test
	void testGetLastOperation() {
		PostBot bot = new PostBot(new TestPostApi(), new TestIndexApi());
		bot.reaction(new MyMessage("/info"));
		MyMessage[] answers = bot.reaction(new MyMessage("123"));
		
		assertEquals("Вручение Мне\n12-12-2011\nЕкатеринбург", answers[0].getText());
	}
	
	@Test
	void testGetAllOperations() {
		PostBot bot = new PostBot(new TestPostApi(), new TestIndexApi());
		bot.reaction(new MyMessage("/full"));
		MyMessage[] answers = bot.reaction(new MyMessage("123"));
		
		assertEquals("Прием Партионный\n11-12-2011\nЕкатеринбург", answers[0].getText());
		assertEquals("Вручение Мне\n12-12-2011\nЕкатеринбург", answers[1].getText());
	}

}

class TestPostApi implements IPostAPI {

	@Override
	public PostOperation[] getFullHistory(String trackNumber) {
		return new PostOperation[] {
				new PostOperation("Прием", "11-12-2011", "Екатеринбург", "", "Партионный"),
				new PostOperation("Вручение", "12-12-2011", "Екатеринбург", "", "Мне")
		};
	}
	
}

class TestIndexApi implements IIndexAPI {

	@Override
	public Double[] getCoordinates(String index) {
		return null;
	}
	
}
