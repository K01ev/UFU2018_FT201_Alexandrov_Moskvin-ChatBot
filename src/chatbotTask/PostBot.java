package chatbotTask;

import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;

public class PostBot implements IChatBot{
	
	private IPostAPI postApi;
	
	public PostBot(IPostAPI postApi) {
		this.postApi = postApi;
	}

	private String[] reaction(String message) {
		switch(message) {
		case "/help":
			return new String[] {getHelpStr()};
		default:
			return new String[] {postApi.getPackageInfo(message) };
		}
	}

	@Override
	public String getName() {
		return "post_bot";
	}

	@Override
	public MyMessage[] getHelp() {
		return new MyMessage[] {new MyMessage(getHelpStr())};
	}
	
	
	public String getHelpStr() {
		return Info.postBotHelp;
	}

	@Override
	public MyMessage[] reaction(MyMessage message) {
		if (message.hasText()) {
			String[] strAnswers = reaction(message.getText());
			MyMessage[] answers = new MyMessage[strAnswers.length];
			for (int i = 0; i < answers.length; i++) {
				answers[i] = new MyMessage();
				answers[i].setText(strAnswers[i]);
			}
			return answers;
		}
		return new MyMessage[0];
	}
}
