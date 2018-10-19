package chatbotTask;

import org.telegram.telegrambots.ApiContextInitializer;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.exceptions.TelegramApiRequestException;

public class Main {
	//String path = System.getProperties("user.dir");
	public static void main(String[] args) {	
		//System.setProperty("java.net.preferIPv4Stack" , "true");
		//System.getProperties().put( "proxySet", "true" );
		//System.getProperties().put( "socksProxyHost", "127.0.0.1" );
		//System.getProperties().put( "socksProxyPort", "9150" );
		
		ApiContextInitializer.init();
		
		TelegramBotsApi tApi = new TelegramBotsApi();
		try {
			tApi.registerBot(new TelegramAPI(
					new SubbotChangerBotFactory(new IChatBotFactory[] {
						new StupidChatBotFactory(),
						new PostBotFactory()
					}, "")));
		} catch (TelegramApiRequestException e) {
			e.printStackTrace();
		}
	}
}	