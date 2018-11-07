package chatbotTask;

public interface IChatBot {

	public String getName();
	
	public MyMessage[] getHelp();
	
	public MyMessage[] reaction(MyMessage message);
	
}
