package chatbotTask;

import org.apache.http.HttpHost;
import org.apache.http.client.config.RequestConfig;
import org.telegram.telegrambots.ApiContextInitializer;
import org.telegram.telegrambots.bots.DefaultBotOptions;
import org.telegram.telegrambots.meta.ApiContext;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.exceptions.TelegramApiRequestException;

public class Main {
	private static boolean needProxy = true;
	private static String proxyHost = "91.239.81.38";
	private static int proxyPort = 51621;
	private static String botName = System.getenv("MY_BOT_NAME");
	private static String token = System.getenv("MY_BOT_TOKEN");

	// String path = System.getProperties("user.dir");
	public static void main(String[] args) {
		// System.setProperty("java.net.preferIPv4Stack" , "true");
		// System.getProperties().put( "proxySet", "true" );
		// System.getProperties().put( "socksProxyHost", "127.0.0.1" );
		// System.getProperties().put( "socksProxyPort", "9150" );

		ApiContextInitializer.init();

		TelegramBotsApi tApi = new TelegramBotsApi();
		try {
			DefaultBotOptions botOptions = ApiContext.getInstance(DefaultBotOptions.class);
			if (needProxy) {
				HttpHost httpHost = new HttpHost(proxyHost, proxyPort);
				RequestConfig requestConfig = RequestConfig.custom().setProxy(httpHost).build();
				botOptions.setRequestConfig(requestConfig);
			}
			TelegramAPI bot = new TelegramAPI(
					new SubbotChangerBotFactory(
						new IChatBotFactory[] { 
							new ChatBotFactory(),
							new PostBotFactory() }, 
						"question_answer_bot"),
					token, 
					botName,
					botOptions);
			System.out.println("Start");
			tApi.registerBot(bot);	
		}
		catch (TelegramApiRequestException e) {
			e.printStackTrace();
		}
		finally {
			System.out.println("End");
		}
	}
}