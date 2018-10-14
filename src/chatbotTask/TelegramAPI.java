package chatbotTask;


import java.util.HashMap;

import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

public class TelegramAPI extends TelegramLongPollingBot
{

	private HashMap<Long, IChatBot> chatBots;
	private IChatBotFactory chatBotFactory;
	
	
	public TelegramAPI(IChatBotFactory factory) {
		chatBots = new HashMap<Long, IChatBot>();
		chatBotFactory = factory;
	}
	
	
	@Override
	public String getBotUsername() {
		return "";
	}

	@Override
	public void onUpdateReceived(Update update) {
		if (update.hasMessage() && (update.getMessage().hasText())) {
			Message message = update.getMessage();
			Long chatID = update.getMessage().getChatId();
			if (!chatBots.containsKey(chatID))
				chatBots.put(chatID, chatBotFactory.getNewChatBot());
			SendMessage sendMessageRequest = new SendMessage();
			sendMessageRequest.setChatId(chatID);
			
			String[] answers = chatBots.get(chatID).reaction(message.getText());
			for (String answer : answers)
			{
				sendMessageRequest.setText(answer);
				try {
					execute(sendMessageRequest);
				} catch (TelegramApiException e) {
					e.printStackTrace();
				}
			}
		}
		
	}

	@Override
	public String getBotToken() {
		return "";
	}
}
