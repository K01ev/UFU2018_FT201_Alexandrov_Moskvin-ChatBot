package chatbotTask;


public class Main {

	//String path = System.getProperties("user.dir");
	public static void main(String[] args) {	
		ChatBot bot = new ChatBot();
		User user = new User(bot, System.in, System.out);
		user.run();
	}
}
