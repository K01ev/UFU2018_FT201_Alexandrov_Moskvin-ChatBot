package chatbotTask;

public class Info {
	public static String help = 
			  "Это помощь бота\n"
			+ "Список команд:\n"
			+ "	/help - показать помощь\n"
			+ "\n"
			+ "Данный бот будет задавать вопросы и проверять правильность ответов";
	
	public static String postBotHelp =
			"Данный бот предоставляет информацию о посылках.\n"
			+ "Список допустимых команд:\n"
			+ "/help - справка\n"
			+ "/info трек-номер - вывести последнюю операцию над объектом\n"
			+ "/full трек-номер - вывести всю историю операций над объектом";
	
	public static String subbotChangerBotHelp = "Subbot changer bot Help\n" +
				"command /changeTo <bot_name> - switch bot on ";
	
	public static QuestionAnswer[] questions = new QuestionAnswer[] {
			new QuestionAnswer("Дважды два?", "4"),
			new QuestionAnswer("Столица Дании?", "Копенгаген")
	};

			
}
