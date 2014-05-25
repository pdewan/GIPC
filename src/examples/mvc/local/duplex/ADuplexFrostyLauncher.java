package examples.mvc.local.duplex;


import bus.uigen.OEFrame;
import bus.uigen.ObjectEditor;
import examples.mvc.local.simplex.ASimplexFrostyLauncher;
import examples.mvc.local.simplex.FrostyConsoleInteractor;
import examples.mvc.local.simplex.ProgramLauncher;


public class ADuplexFrostyLauncher extends ASimplexFrostyLauncher implements ProgramLauncher{
	
	public  void launch() {		
			DuplexFrostyModel clientModel = getFrostyModel();	
			OEFrame oeFrame = ObjectEditor.edit(clientModel);
//			Oval oval = new AnOval(20, 20, 15, 15);
//			oval.setFilled(true);
//			oval.setColor(Color.RED);
//			Oval anotherOval = new AnOval(40, 40, 15, 15);
//			anotherOval.setFilled(true);
//			anotherOval.setColor(Color.BLUE);
//			List telepointers = new AListenableVector();
//			telepointers.add(oval);
//			telepointers.add(anotherOval);
//			oeFrame.setGlassPaneModel(telepointers);
//			oeFrame.setTelePointerModel(telepointers);

//			oeFrame.setGlassPaneModel(oval);
			(new ADuplexFrostyAWTGUI()).interact(clientModel);
			(new ADuplexFrostyVerticalGUI()).interact(clientModel);
//			(new ADuplexFrostyConsoleUI()).interact(clientModel);	
			FrostyConsoleInteractor frostyInteractor =  new ADuplexFrostyConsoleUI();
			frostyInteractor.interact(clientModel);
			frostyInteractor.processConsoleInput();
	
	}
	
	protected DuplexFrostyModel getFrostyModel() {
		Counter counter = getCounter();
		DuplexUpperCaser upperCaser = new  ADuplexUpperCaser(counter);
		return new ADuplexFrostyModel(upperCaser,  counter);

		
	}
	
	protected Counter getCounter() {
		return new ACounter();
	}

	public static void main (String[] args) {
		(new ADuplexFrostyLauncher()).launch();
	}
	

}
