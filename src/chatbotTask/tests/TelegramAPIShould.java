package chatbotTask.tests;


import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import chatbotTask.IChatBot;
import chatbotTask.IChatBotFactory;
import chatbotTask.TelegramAPI;

class TelegramAPIShould {

	@Test
	void testCommutate() {
		TelegramAPI tAPI = new TelegramAPI(new TestChatBotFactory());
		
	}

}