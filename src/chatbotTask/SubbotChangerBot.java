package chatbotTask;

import java.util.HashMap;

import org.apache.commons.lang.ArrayUtils;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;

public class SubbotChangerBot implements IChatBot {
	private HashMap<String, IChatBot> subbotsDict;
	private IChatBot currentSubbot;

	public SubbotChangerBot(IChatBot[] subbots, String startSubbotName) {
		subbotsDict = new HashMap<String, IChatBot>();
		for (IChatBot subbot : subbots) {
			subbotsDict.put(subbot.getName(), subbot);
		}
		if (!subbotsDict.containsKey(startSubbotName))
			throw new IllegalArgumentException("There is no bot named " + startSubbotName);
		currentSubbot = subbotsDict.get(startSubbotName);
	}

	@Override
	public MyMessage[] reaction(MyMessage message) {
		if (message.hasText()) {
			String text = message.getText();
			String firstWord;
			if (text.contains(" "))
				firstWord = text.substring(0, text.indexOf(" "));
			else
				firstWord = text;
			switch (firstWord) {
			case "/changeTo":
				return new MyMessage[] { changeSubbotTo(message) };
			case "/help":
				return getHelp();
			default:
				return currentSubbot.reaction(message);
			}
		}
		return currentSubbot.reaction(message);
	}

	@Override
	public String getName() {
		return "bot_changer_bot";
	}

	@Override
	public MyMessage[] getHelp() {
		return (MyMessage[]) ArrayUtils.add(currentSubbot.getHelp(), 
					new MyMessage(Info.subbotChangerBotHelp));
	}

	private MyMessage changeSubbotTo(MyMessage message) {
		if (message.hasText()) {
			String command = message.getText(); 
			String[] words = command.split("[\n\t\r ]");
			if ((words.length >= 2) && (subbotsDict.containsKey(words[1]))) {
				currentSubbot = subbotsDict.get(words[1]);
				MyMessage sMessage = new MyMessage();				
				sMessage.setText(String.format("Bot has been changed on %s", words[1]));
				return sMessage;
			} else {
				MyMessage sMessage = new MyMessage();				
				sMessage.setText("Undefined bot name.\n" + 
								"These bot names are available:\n" + getSubbotsNameList());
				return sMessage;
			}
		}
		return new MyMessage();
		
	}

	private String getSubbotsNameList() {
		StringBuilder subbotsNameList = new StringBuilder();
		for (String subbotName : subbotsDict.keySet()) {
			subbotsNameList.append(String.format("\t%s\n", subbotName));
		}
		return subbotsNameList.toString();
	}

}
