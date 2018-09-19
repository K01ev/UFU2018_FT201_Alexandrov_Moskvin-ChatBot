package chatbotTask;


public class Main {

	//String path = System.getProperties("user.dir");
	public static void main(String[] args) {	
		ChatBot bot = new ChatBot(new QuestionsRepository());
		IMessageProvider provider = new ConsoleMessageProvider();
		provider.sendMessage(bot.getHelp());
		while(true) {
			String question = bot.getNextQuestion();
			provider.sendMessage(question);
			String message = provider.getMessage();
			
			if (message.equals("/exit"))
				break;
			
			provider.sendMessage(bot.checkAnswer(message));
		}
	}
}	
