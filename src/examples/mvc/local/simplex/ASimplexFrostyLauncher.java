package examples.mvc.local.simplex;

import java.awt.Color;

import javax.swing.JFrame;

import bus.uigen.OEFrame;
import bus.uigen.ObjectEditor;
import bus.uigen.shapes.AnOvalModel;
import shapes.FlexibleShape;




public class ASimplexFrostyLauncher implements ProgramLauncher{	
	public  void launch() {			
			SimplexFrostyModel clientModel = getFrostyModel();	
			OEFrame oeFrame = ObjectEditor.edit(clientModel);
			JFrame jFrame = (JFrame) oeFrame.getFrame().getPhysicalComponent();
			FlexibleShape ovalModel = new AnOvalModel(30, 30, 10, 10);
			ovalModel.setFilled(true);
			ovalModel.setColor(Color.BLUE);
//			oeFrame.setGlassPaneModel(ovalModel);;
			(new ASimplexFrostyAWTGUI()).interact(clientModel);
			(new ASimplexFrostyVerticalGUI()).interact(clientModel);
			FrostyConsoleInteractor frostyInteractor =  new ASimplexFrostyConsoleUI();
			frostyInteractor.interact(clientModel);
			frostyInteractor.processConsoleInput();
//			(new ASimplexFrostyConsoleUI()).interact(clientModel);	
	}

	protected SimplexFrostyModel getFrostyModel() {
		SimplexUpperCaser upperCaser = new  ASimplexUpperCaser();
		return new ASimplexFrostyModel(upperCaser);		
	}
	public static void main (String[] args) {
		(new ASimplexFrostyLauncher()).launch();
	}

}
