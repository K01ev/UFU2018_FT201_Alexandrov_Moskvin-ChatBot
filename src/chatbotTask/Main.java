package chatbotTask;

import java.util.Scanner;

import org.telegram.telegrambots.ApiContextInitializer;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.exceptions.TelegramApiRequestException;


public class Main {

	//String path = System.getProperties("user.dir");
	public static void main(String[] args) {	
		
		ApiContextInitializer.init();
		
		TelegramBotsApi tApi = new TelegramBotsApi();
		try {
			tApi.registerBot(new TelegramAPI(new StupidChatBotFactory()));
		} catch (TelegramApiRequestException e) {
			e.printStackTrace();
		}
		/*
		
		ChatBot bot = new ChatBot(
				new StupidQuestionsRepository(
					new QuestionAnswer[] {new QuestionAnswer("Дважды два?", "4"),
					new QuestionAnswer("Столица Дании", "Копенгаген")})
			);
		Scanner scanner = new Scanner(System.in);
		
		print(bot.reaction("/help"));
		
		while(true) {
			String[] answers = bot.reaction(scanner.nextLine());
			print(answers);
		}*/
	}
	
	public static void print(String[] messages) {
		for (String message : messages) {
			System.out.println(message);
		}
	}
}	