package telegram;


import java.util.concurrent.ConcurrentHashMap;

import org.telegram.telegrambots.bots.DefaultBotOptions;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.ApiContext;
import org.telegram.telegrambots.meta.api.methods.send.SendLocation;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import chatbotTask.IChatBot;
import chatbotTask.IChatBotFactory;
import chatbotTask.MyMessage;

public class TelegramAPI extends TelegramLongPollingBot
{
	private String token;
	private String username;
	private ConcurrentHashMap<Long, IChatBot> chatBots;
	private IChatBotFactory chatBotFactory;
	
	
	public TelegramAPI(IChatBotFactory factory,
					   String botToken, 
					   String botUsername,
		  			   DefaultBotOptions defBotOpt) 
	{
		super(defBotOpt);
		token = botToken;
		username  = botUsername;
		chatBots = new ConcurrentHashMap<Long, IChatBot>();
		chatBotFactory = factory;
	}
	
	public TelegramAPI(IChatBotFactory factory,
			   		   String botToken, 
			   		   String botUsername) {
		this(factory, botToken, botUsername,
				ApiContext.getInstance(DefaultBotOptions.class));
	}
	

	@Override
	public void onUpdateReceived(Update update) {
		if (update.hasMessage()) 
		{	
			System.out.println(update.getMessage().getChatId());
			TelegramMessage[] answerMessages = commutate(update.getMessage());
			for (TelegramMessage answer : answerMessages)
			{
				try {
					if (answer.hasSendMessage()) {
						execute(answer.getSendMessage());
					}
					if (answer.hasSendLocation()) {
						execute(answer.getSendLocation());
					}
				} catch (TelegramApiException e) {
					e.printStackTrace();
				}
			}	
		}
	}
	
	public TelegramMessage[] commutate(Message message) {
		Long chatID = message.getChatId();
		chatBots.putIfAbsent(chatID, chatBotFactory.getNewChatBot());
		IChatBot bot = chatBots.get(chatID);
		MyMessage sendM = Converter.TelegrammMessageToMyMessage(message);
		MyMessage[] answers = bot.reaction(sendM);
		
		TelegramMessage[] tMessages = new TelegramMessage[answers.length];
		for (int i = 0; i < answers.length; i++) {
			tMessages[i] = new TelegramMessage();
			SendMessage sendMessage = Converter.MyMessageToTelegrammSendMessage(answers[i]);
			sendMessage.setChatId(chatID);
			tMessages[i].setSendMessage(sendMessage);
			SendLocation sendLocation = Converter.MyMessageToSendLocation(answers[i]);
			if (sendLocation != null) {
				sendLocation.setChatId(chatID);
				tMessages[i].setSendLocation(sendLocation);
			}
		}
		return tMessages;
	}


	@Override
	public String getBotUsername() {
		return username;
	}


	@Override
	public String getBotToken() {
		return token;
	}

	
}
