package telegram;

import java.util.ArrayList;
import java.util.List;

import org.telegram.telegrambots.meta.api.methods.send.SendLocation;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardButton;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardRow;

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
		
		if (message.hasReplyButtons()) {
			ReplyKeyboardMarkup keys = new ReplyKeyboardMarkup();
			List<KeyboardRow> keyboard = new ArrayList<KeyboardRow>();
			for (List<String> buttonsRow : message.getReplyButtons()) {
				KeyboardRow keyboardRow = new KeyboardRow();
				for (String button : buttonsRow) {
					keyboardRow.add(new KeyboardButton(button));
				}
				keyboard.add(keyboardRow);
			}
			keys.setKeyboard(keyboard);
			keys.setOneTimeKeyboard(true);
			sMessage.setReplyMarkup(keys);
		}
		
		return sMessage;
	}
	
	public static SendLocation MyMessageToSendLocation(MyMessage message) {
		if (message.hasCoordinates()) {
			SendLocation sendLocation = new SendLocation();
			Float[] location = message.getCoordinates();
			sendLocation.setLatitude(location[0]);
			sendLocation.setLongitude(location[1]);
			return sendLocation;
		}
		return null;
	}
}
