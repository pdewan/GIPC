package examples.mvc.local.simplex;

import util.misc.Console;

public class ASimplexFrostyConsoleUI implements FrostyConsoleInteractor  {
	protected SimplexFrostyModel clientModel;
//	public ASimplexFrostyConsoleUI(SimplexFrostyModel aModel) {
//		clientModel = aModel;
//	}
	
	public void interact (SimplexFrostyModel aModel) {
		clientModel = aModel;
//		while (true) {
//			System.out.println(clientModel.FROSTY_PROMPT);
//		    clientModel.setInput (Console.readString());
//		}
	}
	
	public void processConsoleInput() {
		while (true) {
			System.out.println(clientModel.FROSTY_PROMPT);
		    clientModel.setInput (Console.readString());
		}		
	}
}
