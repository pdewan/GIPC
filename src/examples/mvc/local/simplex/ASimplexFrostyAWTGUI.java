package examples.mvc.local.simplex;

import java.awt.Dimension;
import java.awt.Frame;
import java.awt.GridLayout;

import util.awt.AJTextField;
import util.awt.TextComponentInterface;


public class ASimplexFrostyAWTGUI  extends  ASimplexFrostyGUI{

//	public ASimplexFrostyAWTGUI(SimplexFrostyModel anInputProcessor) {
//		super(anInputProcessor);
//	}
	
	protected void layoutAndSizeFrame (Frame frame) {
		frame.setLayout(new GridLayout(1, 1));
		frame.setSize(new Dimension(250, 75));

	}	
	
	protected TextComponentInterface createTextField(String text) {
		return new AJTextField(text);
	}		

}
