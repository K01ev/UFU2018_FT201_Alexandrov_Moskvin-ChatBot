package chatbotTask.tests;

import chatbotTask.IChatBot;
import chatbotTask.IChatBotFactory;

class TestChatBotFactory implements IChatBotFactory
{

	@Override
	public IChatBot getNewChatBot() {
		
		return new TestChatBot();
	}
}