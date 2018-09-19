package chatbotTask;

/*  взаимодействие пользователя с генератором, в перспективе
 *  добавление функционала по каждому пользователю (счет, повтор вопросов 
 *  при неправильном ответе, режимы)
 */
public class SimpleUser implements IUser {   
	private IChatBot bot;
	private IMessageProvider provider;
	
	
	public SimpleUser(IChatBot bot, IMessageProvider provider) {
		this.bot = bot;
		this.provider = provider;
	}
	
	
	
}
