package telegram;

import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;

import chatbotTask.MyMessage;

public class Converter {

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
