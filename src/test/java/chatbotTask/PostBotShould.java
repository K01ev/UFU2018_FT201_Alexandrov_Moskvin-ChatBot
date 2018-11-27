package chatbotTask;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

import containers.Location;
import containers.MyMessage;

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
	
	@Test
	void testGetLastOperationNoCoordinates() {
		PostBot bot = new PostBot(new TestPostApi(), new TestIndexApi());
		bot.reaction(new MyMessage("/info"));
		MyMessage[] answers = bot.reaction(new MyMessage("123"));
		
		assertEquals(false, answers[0].hasCoordinates());
	}
	
	@Test
	void testGetLastOperationCorrectCoordinates() {
		PostBot bot = new PostBot(new TestPostApi(), new TestIndexApi(new Location(0, 0)));
		bot.reaction(new MyMessage("/info"));
		MyMessage[] answers = bot.reaction(new MyMessage("123"));
		
		assertEquals(true, answers[0].hasCoordinates());
		assertEquals(0, answers[0].getCoordinates().latitude);
		assertEquals(0, answers[0].getCoordinates().longitude);
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
	
	private final Location answer;
	
	public TestIndexApi() {
		this(null);
	}
	
	public TestIndexApi(Location answer) {
		this.answer = answer;
	}

	@Override
	public Location getCoordinates(String index) {
		return answer;
	}
	
}
