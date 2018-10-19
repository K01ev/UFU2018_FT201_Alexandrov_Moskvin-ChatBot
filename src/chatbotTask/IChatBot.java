package chatbotTask;

public interface IChatBot {

	public String getName();
	
	public String getHelp();
	
	public String[] reaction(String message);
	
	//public String repeatLastMessage();
}
