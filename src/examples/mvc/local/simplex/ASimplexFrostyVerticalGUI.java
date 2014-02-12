package examples.mvc.local.simplex;

import java.awt.Dimension;
import java.awt.Frame;
import java.awt.GridLayout;

import examples.mvc.local.duplex.ADuplexFrostyGUI;
import examples.mvc.local.duplex.DuplexFrostyModel;


import util.awt.AVerticalTextField;
import util.awt.TextComponentInterface;


public class ASimplexFrostyVerticalGUI  extends  ASimplexFrostyGUI{

//	public ASimplexFrostyVerticalGUI(SimplexFrostyModel anInputProcessor) {
//		super(anInputProcessor);
//	}
	
	protected void layoutAndSizeFrame (Frame frame) {
		frame.setLayout(new GridLayout(1, 1));
		frame.setSize(new Dimension(100, 400));

	}	
	protected TextComponentInterface createTextField(String text) {
		return new AVerticalTextField(text);
	}		

}
