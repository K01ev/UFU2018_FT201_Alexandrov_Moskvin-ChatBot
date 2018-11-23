package telegram;

import org.telegram.telegrambots.meta.api.methods.send.SendLocation;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;

public class TelegramMessage {
	private SendMessage sendMessage;
	private SendLocation sendLocation;
	
	public boolean hasSendMessage() {
		return sendMessage != null;
	}
	
	public boolean hasSendLocation() {
		return sendLocation != null;
	}
	
	public void setSendMessage(SendMessage message) {
		sendMessage = message;
	}
	
	public void setSendLocation(SendLocation location) {
		sendLocation = location;
	}
	
	public SendMessage getSendMessage() {
		return sendMessage;
	}
	
	public SendLocation getSendLocation() {
		return sendLocation;
	}
}
