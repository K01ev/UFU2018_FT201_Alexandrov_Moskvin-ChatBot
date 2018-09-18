package chatbotTask;

import java.util.Scanner;

public class Main {

	//String path = System.getProperties("user.dir");
	public static void main(String[] args) {
		PrintHelp();
		Scanner scanner = new Scanner(System.in);
		boolean repeat = true;
		for (int i = 1; repeat; i++) {
			System.out.print("Question number ");
			System.out.println(i);
			String answer = scanner.nextLine().toLowerCase();
			switch (answer)
			{
			case "help":
				PrintHelp();
				break;
			case "exit":
				repeat = false;
				break;
			default:
				System.out.println((answer.equals("yes")) ? "Correct" : "Incorrect");
				break;
			}
		}
		System.out.println("Goodbye!");
		
	}
	
	private static void PrintHelp() {
		System.out.println("Hello, I am Bot. This is help");
	}

}
