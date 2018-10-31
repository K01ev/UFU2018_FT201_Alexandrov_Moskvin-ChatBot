package chatbotTask;

import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;

public class MyMessage {
	private String text;
	
	public MyMessage() {	}
	
	public MyMessage(String text) {
		this.text = text;
	}
	
	public boolean hasText() {
		return !(text == null) && (text.length() > 0);
	}
	
	public String getText() {
		return text;
	}
	
	public void setText(String str) {
		text = str;
	}
	
	public static MyMessage TelegrammMessageToMyMessage(Message message) {
		MyMessage mess =  new MyMessage();
		if (message.hasText()) 
			mess.setText(message.getText());
		return mess;
	}
	
	public static SendMessage MyMessageToTelegrammSendMessage(MyMessage message) {
		SendMessage sMessage = new SendMessage();
		if (message.hasText())
			sMessage.setText(message.getText());
		return sMessage;
	}
}
