package examples.mvc.local.duplex;

import java.awt.Dimension;
import java.awt.Frame;
import java.awt.GridLayout;

import util.awt.AVerticalTextField;
import util.awt.TextComponentInterface;


public class ADuplexFrostyVerticalGUI  extends  ADuplexFrostyGUI{

//	public ADuplexFrostyVerticalGUI(DuplexFrostyModel anInputProcessor) {
//		super(anInputProcessor);
//	}	
	protected void layoutAndSizeFrame (Frame frame) {
		frame.setLayout(new GridLayout(1, 2));
		frame.setSize(new Dimension(200, 400));
	}	
	protected TextComponentInterface createTextField(String text) {
		return new AVerticalTextField(text);
	}		

}
