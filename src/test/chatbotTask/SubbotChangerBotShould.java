package test.chatbotTask;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import chatbotTask.IChatBot;
import chatbotTask.Info;
import chatbotTask.MyMessage;
import chatbotTask.SubbotChangerBot;

class SubbotChangerBotShould {
	
	IChatBot test1 = new TestChatBot(
			"testBot1", new MyMessage[] {new MyMessage("Help1")},
			new MyMessage[] {new MyMessage("Answer1")});
	IChatBot test2 = new TestChatBot(
			"testBot2", new MyMessage[] {new MyMessage("help 2 test bot")},
			new MyMessage[] {new MyMessage("Answer2")});

	@Test
	void testCorrectHelpOnChange() {
		SubbotChangerBot changer = 
				new SubbotChangerBot(new IChatBot[] {test1, test2}, "testBot1");
		MyMessage[] helps = changer.getHelp();
		assertEquals(2, helps.length);
		assertEquals(test1.getHelp()[0].getText(), helps[0].getText());
		assertEquals(Info.subbotChangerBotHelp, helps[1].getText());
		
		changer.reaction(new MyMessage("/changeTo testBot2"));
		helps = changer.getHelp();
		assertEquals(2, helps.length);
		assertEquals(test2.getHelp()[0].getText(), helps[0].getText());
		assertEquals(Info.subbotChangerBotHelp, helps[1].getText());
	}
	
	@Test
	void testCorrectChange() 
	{
		SubbotChangerBot changer = 
				new SubbotChangerBot(new IChatBot[] {test1, test2}, "testBot1");
		MyMessage[] answers = changer.reaction(new MyMessage("Hello T1"));
		assertEquals(1, answers.length);
		assertEquals("Answer1", answers[0].getText());
		
		answers = changer.reaction(new MyMessage("/changeTo " + test2.getName()));
		assertEquals("Bot has been changed on "+test2.getName() ,answers[0].getText());
		
		answers = changer.reaction(new MyMessage("Hello T2"));
		assertEquals(1, answers.length);
		assertEquals("Answer2", answers[0].getText());
		
	}
	
	@Test
	void testIncorrectChange() {
		SubbotChangerBot changer = 
				new SubbotChangerBot(new IChatBot[] {test1,test2}, "testBot1");
		MyMessage[] answers = changer.reaction(new MyMessage("Hello T1"));
		assertEquals(1, answers.length);
		assertEquals("Answer1", answers[0].getText());
		
		answers = changer.reaction(new MyMessage("/changeTo SMTHWRONG"));
		assertEquals(1, answers.length);
		assertEquals("Undefined bot name.\n" + 
				"These bot names are available:\n" +
				"\ttestBot2\n" +
				"\ttestBot1\n",
				answers[0].getText());
		
		answers = changer.reaction(new MyMessage("Hello T2"));
		assertEquals(1, answers.length);
		assertEquals("Answer1", answers[0].getText());
		

	}

}