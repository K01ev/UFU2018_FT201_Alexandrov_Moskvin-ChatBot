package chatbotTask;

public class PostBot implements IChatBot{
	
	private IPostAPI postApi;
	private final String error = "Некорректная команда.";
	private final String fail = "Не удалось получить информацию.";
	
	public PostBot(IPostAPI postApi) {
		this.postApi = postApi;
	}

	private String[] reaction(String message) {
		String firstWord = getFirstWord(message);
		String[] cmdAndData = message.split("[\n\t\r ]");
		
		switch(firstWord) {
		case "/help":
		case "/start":
			return new String[] {getHelpStr()};
		case "/info":
			return (cmdAndData.length >= 2) ? 
					getLastOperation(cmdAndData[1]) : new String[] {error};
		case "/full":
			return (cmdAndData.length >= 2) ? 
					getFullHistory(cmdAndData[1]) : new String[] {error};
		default:
			return new String[] {error};
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
	
	public String[] getLastOperation(String trackNumber) {
		String[] history = getFullHistory(trackNumber);
		return new String[] { history[history.length - 1] };
	}
	
	public String[] getFullHistory(String trackNumber) {
		PostOperation[] history = postApi.getFullHistory(trackNumber);
		if (history == null || history.length == 0) {
			return new String[] {fail};
		}
		
		String[] result = new String[history.length];
		for (int i = 0; i < history.length; i++) {
			result[i] = history[i].type + "\n" + history[i].date;
		}
		return result;
	}
}
