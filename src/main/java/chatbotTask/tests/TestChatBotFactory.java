package chatbotTask.tests;

import chatbotTask.IChatBot;
import chatbotTask.IChatBotFactory;

public class TestChatBotFactory implements IChatBotFactory
{

	@Override
	public IChatBot getNewChatBot() {
		
		return new TestChatBot();
	}
}