package chatbotTask;


public class SubbotChangerBotFactory implements IChatBotFactory{

	private String startSubbotName;
	private IChatBotFactory[] subbotFactories;
	
	public SubbotChangerBotFactory(IChatBotFactory[] subbotFactorys,
								   String startName) 
	{
		startSubbotName = startName;
		this.subbotFactories = subbotFactorys;
	}
		
		
	@Override
	public IChatBot getNewChatBot() {
		IChatBot[] subbots = new IChatBot[subbotFactories.length];
		for (int i = 0; i < subbots.length; i++) {
			subbots[i] = subbotFactories[i].getNewChatBot();
		}
		return new SubbotChangerBot(subbots, startSubbotName);
	}

}
