package chatbotTask;

public class Info {
	public static String help = 
			  "Это помощь бота\n"
			+ "Список команд:\n"
			+ "	/help - показать помощь\n"
			+ "\n"
			+ "Данный бот будет задавать вопросы и проверять правильность ответов";
	
	public static String postBotHelp = "Post bot help";
	
	public static String subbotChangerBotHelp = "Subbot changer bot Help\n" +
				"command /changeTo <bot_name> - switch bot on ";
	
	public static QuestionAnswer[] questions = new QuestionAnswer[] {
			new QuestionAnswer("Дважды два?", "4"),
			new QuestionAnswer("Столица Дании?", "Копенгаген")
	};
}
