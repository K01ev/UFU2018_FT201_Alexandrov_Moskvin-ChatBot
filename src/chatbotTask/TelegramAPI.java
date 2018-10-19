package chatbotTask;


import java.util.HashMap;

import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

public class TelegramAPI extends TelegramLongPollingBot
{
	private final String token = "630155739:AAHjfvBPikiGsJOOG8K6vMloJQAHSdtqHmM";
	private final String botUsername = "QuestionAnswerBot";
	private HashMap<Long, IChatBot> chatBots;
	private IChatBotFactory chatBotFactory;
	
	
	public TelegramAPI(IChatBotFactory factory) {
		chatBots = new HashMap<Long, IChatBot>();
		chatBotFactory = factory;
	}
	
	
	@Override
	public String getBotUsername() {
		return botUsername;
	}

	@Override
	public void onUpdateReceived(Update update) {
		if (update.hasMessage()) 
		{	
			SendMessage[] answerMessages = commutate(update.getMessage());
			for (SendMessage answer : answerMessages)
			{
				try {
					execute(answer);
				} catch (TelegramApiException e) {
					e.printStackTrace();
				}
			}	
		}
	}
	
	private SendMessage[] commutate(Message message) {
		if (message.hasText()) {
			Long chatID = message.getChatId();
			if (!chatBots.containsKey(chatID))
				chatBots.put(chatID, chatBotFactory.getNewChatBot());
			String[] answers = chatBots.get(chatID).reaction(message.getText());
			SendMessage[] answerMessages = new SendMessage[answers.length];
			for (int i = 0; i < answers.length; i++) {
				answerMessages[i] = new SendMessage();
				answerMessages[i].setChatId(chatID);
				answerMessages[i].setText(answers[i]);
			}
			return answerMessages;
		}
		return new SendMessage[0];
	}

	@Override
	public String getBotToken() {
		return token;
	}
}
