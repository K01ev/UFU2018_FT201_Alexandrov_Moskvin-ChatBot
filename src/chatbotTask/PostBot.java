package chatbotTask;

public class PostBot implements IChatBot{
	
	private IPostAPI postApi;
	
	public PostBot(IPostAPI postApi) {
		this.postApi = postApi;
	}

	@Override
	public String[] reaction(String message) {
		switch(message) {
		case "/help":
			return new String[] {getHelp()};
		default:
			return new String[] {postApi.getPackageInfo(message) };
		}
	}

	@Override
	public String getName() {
		return "post_bot";
	}

	@Override
	public String getHelp() {
		return Info.postBotHelp;
	}

}
