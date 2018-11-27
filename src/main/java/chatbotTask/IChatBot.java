package chatbotTask;

import containers.MyMessage;

public interface IChatBot {

	public String getName();
	
	public MyMessage[] getHelp();
	
	public MyMessage[] reaction(MyMessage message);
	
	default String getFirstWord(String text) {
		String firstWord;
		if (text.contains(" "))
			firstWord = text.substring(0, text.indexOf(" "));
		else
			firstWord = text;
		return firstWord;
	}
	
	default String getFirstWord(MyMessage message) {
		return getFirstWord(message.getText());
	}
	
}
