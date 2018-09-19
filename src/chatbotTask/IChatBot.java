package chatbotTask;

public interface IChatBot { // генератор вопросов
	
	public Pair<Integer, String> getNextQuestion();
	
	public boolean checkAnswer(int id, String answer);

	public String getHelp();
}
