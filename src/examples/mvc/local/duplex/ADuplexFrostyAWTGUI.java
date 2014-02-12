package examples.mvc.local.duplex;

import java.awt.Dimension;
import java.awt.Frame;
import java.awt.GridLayout;

import util.awt.AJTextField;
import util.awt.TextComponentInterface;


public class ADuplexFrostyAWTGUI  extends  ADuplexFrostyGUI{
//	public ADuplexFrostyAWTGUI(DuplexFrostyModel anInputProcessor) {
//		super(anInputProcessor);
//	}	
	protected void layoutAndSizeFrame (Frame frame) {
		frame.setLayout(new GridLayout(2, 1));
		frame.setSize(new Dimension(250, 100));
	}	
	protected TextComponentInterface createTextField(String text) {
		return new AJTextField(text);
	}		

}
