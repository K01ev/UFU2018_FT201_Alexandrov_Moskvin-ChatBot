package chatbotTask;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

public class PostBot implements IChatBot{
	
	enum State { INFO, FULLINFO, WAITING }
	
	private IPostAPI postApi;
	private IIndexAPI indexApi;
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
	private HashMap<String, PostOperation[]> trackOperationsHistory ;
	
	public PostBot(IPostAPI postApi, IIndexAPI indexApi) {
		this.postApi = postApi;
		this.indexApi = indexApi;
		state = State.WAITING;
		System.out.println(state.toString());
		tracks = new ArrayDeque<String>();
		trackOperationsHistory = new HashMap<String, PostOperation[]>();
	}

	private void updateFields(String trackNumber) {
		state = State.WAITING;
		if (updateHistory(trackNumber)) {
			if (!tracks.contains(trackNumber)) {
				tracks.offer(trackNumber);
			}
			while (tracks.size() > maxSize) {
				tracks.poll();
			}
		}
	}

	private MyMessage waitingStateReaction(String command) {
		switch(command) {
		case "/help":
		case "Help":
			return new MyMessage(getHelpStr());
		case "/info":
		case "Info":
			state = State.INFO;
			return new MyMessage(invite);
		case "/full":
		case "Full info":
			state = State.FULLINFO;
			return new MyMessage(invite);
		default:
			return new MyMessage(error);
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
	
	private PostOperation[] updateAndGetHistory(String firstWord) {
		updateFields(firstWord);
		return trackOperationsHistory.getOrDefault(firstWord, null);
	}

	private MyMessage[] _reaction(MyMessage message) {
		if (message.hasText()) {
			String firstWord = getFirstWord(message);
			PostOperation[] history;
			
			if (firstWord == "/start") {
				reset();
				return new MyMessage[] {new MyMessage(getHelpStr())};
			}
			
			switch (state) {
			case INFO:
				history = updateAndGetHistory(firstWord);
				if (history == null) {
					return new MyMessage[] {new MyMessage(fail)};
				}
				return new MyMessage[] {postOperationToMyMessage(history[history.length - 1])};
			case FULLINFO:
				history = updateAndGetHistory(firstWord);
				if (history == null) {
					return new MyMessage[] {new MyMessage(fail)};
				}
				return postOperationsToMyMessages(history);
			case WAITING:
				return new MyMessage[] {waitingStateReaction(message.getText())};
			default:
				String error = "Что-то пошло не так. Обнаружено состояние: " + state.toString();
				System.out.println(error);
				return new MyMessage[] {new MyMessage(error)};
			}

		}
		return new MyMessage[0];
	}
	
	@Override
	public MyMessage[] reaction(MyMessage message) {
		MyMessage[] answers = _reaction(message);
		answers[answers.length -1].setReplyButtons(getKeyboard(answers));
		return answers;
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
	
	
	private boolean updateHistory(String trackNumber) {
		PostOperation[] history = postApi.getFullHistory(trackNumber);
		if (history == null || history.length == 0) {
			return false;
		}
		trackOperationsHistory.put(trackNumber, history);
		return true;
	}
	
	private String parseDate(String date) {
		SimpleDateFormat parser = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSXXX");
		try {
			Date d = parser.parse(date);
			SimpleDateFormat formatter = new SimpleDateFormat("EEE, d MMM yyyy HH:mm:ss");
	        String formattedDate = formatter.format(d);
	        return formattedDate;
		} catch (ParseException e) {
			e.printStackTrace();
			return date;
		}
	}
	
	private MyMessage postOperationToMyMessage(PostOperation operation) {
		MyMessage message = new MyMessage();
		message.setText(operation.type + " " + operation.attr + "\n" + parseDate(operation.date) + 
				"\n" + operation.addr);
		Double[] coordinates = indexApi.getCoordinates(operation.index);
		if (coordinates != null) {
			message.setCoordinates(new Float(coordinates[0]), new Float(coordinates[1]));
		}
		return message;
	}
	
	private MyMessage[] postOperationsToMyMessages(PostOperation[] operations) {
		MyMessage[] messages = new MyMessage[operations.length];
		
		for (int i = 0; i < operations.length; i++) {
			messages[i] = postOperationToMyMessage(operations[i]);
		}
		return messages;
	}
	
}
