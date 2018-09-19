package chatbotTask;

import java.util.Scanner;

public class ConsoleMessageProvider implements IMessageProvider {

	private Scanner scanner;
	
	public ConsoleMessageProvider() {
		scanner = new Scanner(System.in);
	}
	
	
	@Override
	public String getMessage() {
		return scanner.nextLine();
	}

	@Override
	public void sendMessage(String message) {
		System.out.println(message);
	}

}
