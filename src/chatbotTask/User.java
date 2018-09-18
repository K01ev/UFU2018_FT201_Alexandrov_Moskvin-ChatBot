package chatbotTask;

import java.io.InputStream;
import java.io.OutputStream;

public class User {
	private ChatBot bot;
	private InputStream input;
	private OutputStream output;
	
	
	public User(ChatBot bot, InputStream input, OutputStream output) {
		this.bot = bot;
		this.input = input;
		this.output = output;
	}
	
	
	public void run() {
		
	}
}
