package chatbotTask;

import java.util.Scanner;


public class Main {

	//String path = System.getProperties("user.dir");
	public static void main(String[] args) {	
		ChatBot bot = new ChatBot(
				new QuestionsRepository(
					new QuestionAnswer[] {new QuestionAnswer("Дважды два?", "4"),
					new QuestionAnswer("Столица Дании", "Копенгаген")})
			);
		Scanner scanner = new Scanner(System.in);
		
		print(bot.reaction("/help"));
		
		while(true) {
			String[] answers = bot.reaction(scanner.nextLine());
			print(answers);
		}
	}
	
	public static void print(String[] messages) {
		for (String message : messages) {
			System.out.println(message);
		}
	}
}	