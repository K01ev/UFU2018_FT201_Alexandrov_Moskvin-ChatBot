package chatbotTask;

public class MyMessage {
	private String text;
	
	public MyMessage() {	}
	
	public MyMessage(String text) {
		this.text = text;
	}
	
	public boolean hasText() {
		return !(text == null) && (text.length() > 0);
	}
	
	public String getText() {
		return text;
	}
	
	public void setText(String str) {
		text = str;
	}
}
