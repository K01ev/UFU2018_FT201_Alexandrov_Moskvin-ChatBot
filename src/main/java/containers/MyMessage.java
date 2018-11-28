package containers;

import java.util.List;

public class MyMessage {
	private String text;
	private boolean hasReplyButtons = false;
	private List<List<String>>  replyButtons;
	private Location coordinates;
	
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
		hasReplyButtons = (buttons != null) ? true : false;
		replyButtons = buttons;
	}
	
	public boolean hasReplyButtons() {
		return hasReplyButtons;
	}
	
	public List<List<String>> getReplyButtons() {
		return replyButtons;
	}
	
	public void setCoordinates(Location location) {
		this.coordinates = location;
	}
	
	public boolean hasCoordinates() {
		return coordinates != null;
	}
	
	public Location getCoordinates() {
		return coordinates;
	}
}
