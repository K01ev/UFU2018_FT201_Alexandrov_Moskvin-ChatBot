package chatbotTask;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class PostBot implements IChatBot{
	
	enum State { INFO, FULLINFO, WAITING }
	
	private IPostAPI postApi;
	private final int maxSize = 5;
	private final String invite = "Введите трек-номер.";
	private final String error = "Некорректная команда.";
	private final String fail = "Не удалось получить информацию.";
	private final List<List<String>> waitingButtonsTable = 
			 Arrays.asList(
			 Arrays.asList(new String[] {"Help"}),
			 Arrays.asList(new String[] {"Info"}),
			 Arrays.asList(new String[] {"Full info"})
		);
	
	private State state;
	private ArrayDeque<String> tracks;
	private HashMap<String, String[]> trackOperationsHistory ;
	
	public PostBot(IPostAPI postApi) {
		this.postApi = postApi;
		state = State.WAITING;
		System.out.println(state.toString());
		tracks = new ArrayDeque<String>();
		trackOperationsHistory = new HashMap<String, String[]>();
	}

	private String[] reaction(String message) {
		String firstWord = getFirstWord(message);
		if (firstWord == "/start") {
			reset();
			return new String[] {getHelpStr()};
		}
		switch (state) {
		case INFO:
			updateFields(firstWord);
			String[] history = 
					trackOperationsHistory.getOrDefault(firstWord, new String[] {fail});
			return new String[] {history[history.length-1]};
		case FULLINFO:
			updateFields(firstWord);
			return trackOperationsHistory.getOrDefault(firstWord, new String[] {fail});
		case WAITING:
			return waitingStateReaction(message);
		default:
			String error = "Что-то пошло не так. Обнаружено состояние: " + state.toString();
			System.out.println(error);
			return new String[] {error};
		}
	}

	private void updateFields(String trackNumber) {
		state = State.WAITING;
		if (updateHistory(trackNumber)) {
			tracks.offer(trackNumber);
			while (tracks.size() > maxSize) {
				tracks.poll();
			}
		}
	}

	private String[] waitingStateReaction(String command) {
		switch(command) {
		case "/help":
		case "Help":
			return new String[] {getHelpStr()};
		case "/info":
		case "Info":
			state = State.INFO;
			return new String[] {invite};
		case "/full":
		case "Full info":
			state = State.FULLINFO;
			return new String[] {invite};
		default:
			return new String[] {error};
		}
	}
	
	private void reset() {
		state = State.WAITING;
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
			answers[answers.length -1].setReplyButtons(getKeyboard(answers));
			return answers;
		}
		return new MyMessage[0];
	}

	private List<List<String>> getKeyboard(MyMessage[] answers) {
		switch(state) {
		case WAITING:
			return waitingButtonsTable;
		case FULLINFO:
		case INFO:
			List<List<String>> buttonsTable = new ArrayList<List<String>>();
			for (String track : tracks) {
				buttonsTable.add(Arrays.asList(new String[] {track}));
			}
			return buttonsTable;
		default:
			String error = "Что-то пошло не так. Обнаружено состояние: " + state.toString();
			System.out.println(error);
			return new ArrayList<List<String>>();
		}
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
	
	public boolean updateHistory(String trackNumber) {
		PostOperation[] history = postApi.getFullHistory(trackNumber);
		if (history == null || history.length == 0) {
			return false;
		}
		String[] result = new String[history.length];
		for (int i = 0; i < history.length; i++) {
			result[i] = history[i].type + "\n" + history[i].date;
		}
		trackOperationsHistory.put(trackNumber, result);
		return true;
	}
	
}
