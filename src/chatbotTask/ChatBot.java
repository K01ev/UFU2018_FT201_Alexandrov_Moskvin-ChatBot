package chatbotTask;

import java.util.ArrayList;;

public class ChatBot implements IChatBot
{
	
	private ArrayList<QuestionAnswer> questions;
	private int index;
	private String help = 
			"Это помощь бота\n"
			+ "Список команд:\n"
			+ "	/help - показать помощь\n"
			+ "\n"
			+ "Данный бот будет задавать вопросы и проверять правильность ответов";
	
	public ChatBot() {
		questions = new ArrayList<QuestionAnswer>();
		questions.add(new QuestionAnswer("Дважды два?", "4"));
		questions.add(new QuestionAnswer("Столица Дании", "Копенгаген"));
		index = 0;
	}
		
	
	
	public Pair<Integer, String> getNextQuestion() {
		
		Pair<Integer, String> question = new Pair<Integer, String>(index, 
						questions.get(index).getQuestion());
		index = (index +1) % questions.size();
		return question;
	}
	
	public boolean checkAnswer(int id, String contender) {
		return questions.get(id).isAnswer(contender);
	}
	
	

	public String getHelp() {
		return help;
	}
	
}
