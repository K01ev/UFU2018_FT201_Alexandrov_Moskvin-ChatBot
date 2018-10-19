package chatbotTask.tests;

import chatbotTask.IChatBot;

public class TestChatBot implements IChatBot {

	@Override
	public String getName() {
		
		return "testBot1";
	}

	@Override
	public String getHelp() {
		return "Help1";
	}

	@Override
	public String[] reaction(String message) {
		return new String[] {message};
	}

}
