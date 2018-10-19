package chatbotTask;

import java.util.HashMap;

public class SubbotChangerBot  implements IChatBot
{
	private HashMap<String, IChatBot> subbotsDict;
	private IChatBot currentSubbot;
	
	public SubbotChangerBot(IChatBot[] subbots, String startSubbotName) {
		subbotsDict = new HashMap<String, IChatBot>();
		for (IChatBot subbot: subbots) {
			subbotsDict.put(subbot.getName(), subbot);
		}
		if (!subbotsDict.containsKey(startSubbotName))
			throw new IllegalArgumentException("There is no bot named " + startSubbotName);
		currentSubbot = subbotsDict.get(startSubbotName);
	}
	
	@Override
	public String[] reaction(String message) {
		String firstWord;
		if (message.contains(" "))
			firstWord = message.substring(0, message.indexOf(" "));
		else 
			firstWord = message;
		switch(firstWord) {
			case "/changeTo":
				return new String[] {changeSubbotTo(message)};
			case "/help":
				return new String[] {getHelp()};
			default:
				return currentSubbot.reaction(message);
		}
	}

	@Override
	public String getName() {
		return "bot_changer_bot";
	}

	@Override
	public String getHelp() {
		return Info.subbotChangerBotHelp + currentSubbot.getHelp();
	}
	
	private String changeSubbotTo(String command) {
		String[] words = command.split("[\n\t\r ]");
		if ((words.length >= 2) && (subbotsDict.containsKey(words[1]))) 
		{
			currentSubbot = subbotsDict.get(words[1]);
			return String.format("Bot has been changed on {}", words[1]);
		}
		else
		{
			return "Undefined bot name.\n"
					+ "These bot names are available:\n" + getSubbotsNameList();
		}
	}
	
	private String getSubbotsNameList() {
		StringBuilder subbotsNameList = new StringBuilder();
		for (String subbotName : subbotsDict.keySet()) {
			subbotsNameList.append(String.format("\t{}\n", subbotName));
		}
		return subbotsNameList.toString();
	}

}
