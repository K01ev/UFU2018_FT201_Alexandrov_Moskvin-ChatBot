package chatbotTask;

import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;

public interface IChatBot {

	public String getName();
	
	public MyMessage[] getHelp();
	
	public MyMessage[] reaction(MyMessage message);
	
	//public String repeatLastMessage();
}
