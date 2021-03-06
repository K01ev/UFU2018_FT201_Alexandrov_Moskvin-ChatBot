package telegram;

import org.apache.http.HttpHost;
import org.apache.http.client.config.RequestConfig;
import org.telegram.telegrambots.ApiContextInitializer;
import org.telegram.telegrambots.bots.DefaultBotOptions;
import org.telegram.telegrambots.meta.ApiContext;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.exceptions.TelegramApiRequestException;

import chatbotTask.PostAPIFactory;
import chatbotTask.PostBotFactory;

public class Main {
	private static boolean needProxy = true;
	private static String proxyHost = "92.46.109.74";
	private static int proxyPort = 42683;
	private static String botName = System.getenv("MY_BOT_NAME");
	private static String token = System.getenv("MY_BOT_TOKEN");

	// String path = System.getProperties("user.dir");
	public static void main(String[] args) {

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
					new PostBotFactory(new PostAPIFactory()),
						token, 
						botName,
						botOptions);
			System.out.println("Start");
			tApi.registerBot(bot);	
		}
		catch (TelegramApiRequestException e) {
			e.printStackTrace();
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		finally {
			System.out.println("End");
		}
	}
}