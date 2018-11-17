package telegram;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Test;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;

import chatbotTask.TestChatBotFactory;
import telegram.TelegramAPI;

class TelegramAPIShould {
	
	Message mockMessage(String text, Long chatId) {
		Message message = mock(Message.class);
		when(message.hasText()).thenReturn(true);
		when(message.getChatId()).thenReturn(chatId);
		when(message.getText()).thenReturn(text);
		
		return message;
	}
	
	void checkParams(SendMessage message, String text, String chatId) {
		assertEquals(text, message.getText());
		assertEquals(chatId, message.getChatId());
	}

	@Test
	void testCommutate() {
		TelegramAPI tAPI = new TelegramAPI(new TestChatBotFactory(), "", "");
		
		Message in = mockMessage("123", 1l);
		SendMessage[] result = tAPI.commutate(in);
		assertEquals(1, result.length);
		SendMessage out = result[0];
		checkParams(out, "123", "1");
	}
	
	@Test
	void testCommutateTwoUsers() {
		TelegramAPI tAPI = new TelegramAPI(new TestChatBotFactory(), "", "");
		
		Message user1Message = mockMessage("123", 1l);
		Message user2Message = mockMessage("456", 2l);
		
		checkParams(tAPI.commutate(user1Message)[0], "123", "1");
		checkParams(tAPI.commutate(user2Message)[0], "456", "2");
	}

}