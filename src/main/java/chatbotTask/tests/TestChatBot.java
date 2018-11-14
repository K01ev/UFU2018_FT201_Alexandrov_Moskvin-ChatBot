package chatbotTask.tests;

import chatbotTask.IChatBot;
import chatbotTask.MyMessage;

public class TestChatBot implements IChatBot {
	
	private String name;
	private MyMessage[] help;
	private MyMessage[] answer;
	
	public TestChatBot(String name, MyMessage[] help, MyMessage[] answer) {
		this.name = name;
		this.help = help;
		this.answer = answer;
	}
	
	public TestChatBot() {
		this("testBot1", new MyMessage[] {new MyMessage("Help1")},
				null);
	}

	@Override
	public String getName() {
		
		return name;
	}

	@Override
	public MyMessage[] getHelp() {
		return help;
	}

	@Override
	public MyMessage[] reaction(MyMessage message) {
		if (answer != null) {
			return answer;
		}
		MyMessage answer = new MyMessage();
		answer.setText(message.getText());
		return new MyMessage[] {answer};
	}

}
