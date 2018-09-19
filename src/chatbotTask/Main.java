package chatbotTask;


public class Main {

	//String path = System.getProperties("user.dir");
	public static void main(String[] args) {	
		IChatBot bot = new ChatBot();
		IMessageProvider provider = new ConsoleMessageProvider();
		provider.sendMessage(bot.getHelp());
		while(true) {
			Pair<Integer, String> questionPair = bot.getNextQuestion();
			int id = questionPair.getFirst();
			provider.sendMessage(questionPair.getSecond());
			String message = provider.getMessage();
			
			if (message.equals("/help")) 
				provider.sendMessage(bot.getHelp());
			else if (message.equals("/exit"))
				break;
			else 
				provider.sendMessage(bot.checkAnswer(id, message) ? 
						"Correct!" : "Incorrect!");
		}
	}
}	
