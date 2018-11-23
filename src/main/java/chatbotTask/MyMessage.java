package chatbotTask;

import java.util.List;

public class MyMessage {
	private String text;
	private List<List<String>>  replyButtons;
	private Float latitude;
	private Float longitude;
	
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
	
	public void setReplyButtons(List<List<String>> buttons) {
		replyButtons = buttons;
	}
	
	public boolean hasReplyButtons() {
		return !((replyButtons == null) || (replyButtons.isEmpty()));
	}
	
	public List<List<String>> getReplyButtons() {
		return replyButtons;
	}
	
	public void setCoordinates(float latitude, float longitude) {
		this.latitude = latitude;
		this.longitude = longitude;
	}
	
	public boolean hasCoordinates() {
		return latitude != null && longitude != null;
	}
	
	public Float[] getCoordinates() {
		return new Float[] {latitude, longitude};
	}
}
