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
		String[] answers;
		answers = bot.reaction("/help");
		for (String answer : answers) {
			System.out.println(answer);
		}
		while(true) {
			answers = bot.reaction(scanner.nextLine());
			for (String answer : answers) {
				System.out.println(answer);
			}
		}
	}
}	
