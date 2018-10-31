package chatbotTask.tests;

import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;

import chatbotTask.IChatBot;
import chatbotTask.MyMessage;

public class TestChatBot implements IChatBot {

	@Override
	public String getName() {
		
		return "testBot1";
	}

	@Override
	public MyMessage[] getHelp() {
		return new MyMessage[] {new MyMessage("Help1")};
	}

	@Override
	public MyMessage[] reaction(MyMessage message) {
		MyMessage answer = new MyMessage();
		answer.setText(message.getText());
		return new MyMessage[] {answer};
	}

}
