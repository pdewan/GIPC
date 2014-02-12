package examples.mvc.local.simplex;

import java.awt.Component;
import java.awt.Frame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JComponent;
import javax.swing.JFrame;

import util.awt.AGlassPaneRedispatcher;
import util.awt.TextComponentInterface;


public abstract class ASimplexFrostyGUI  implements FrostyInteractor, ActionListener{
	protected JFrame frame;
	protected TextComponentInterface inputField;
	protected SimplexFrostyModel clientModel;
	protected abstract void layoutAndSizeFrame (Frame frame);	
	protected abstract TextComponentInterface createTextField(String text) ;
	public ASimplexFrostyGUI() {
	}
	public void interact (SimplexFrostyModel aModel) {
		clientModel = aModel;
		frame = new JFrame(SimplexFrostyModel.FROSTY_PROMPT);
		createAndAddFrameComponents();	
//		createGlassPane();
		layoutAndSizeFrame(frame);
		frame.setVisible(true);		
	}	
	
	void createDrawingFrameContainer() {
		JComponent glassPane = new ATelePointerGlassPane(frame);
		frame.setContentPane(glassPane);
	}	
	protected void createAndAddFrameComponents(){
		inputField = createTextField("")	;	
		frame.add((Component) inputField);
		inputField.addActionListener(this);		
	}
	
	public void actionPerformed(ActionEvent arg0) {
		clientModel.setInput(inputField.getText());
	}	

	void createGlassPane() {
		JComponent glassPane = new ATelePointerGlassPane(frame);
		AGlassPaneRedispatcher redispatcher = new AGlassPaneRedispatcher(glassPane, frame);
		frame.setGlassPane(glassPane);
		glassPane.setVisible(true);
	}
		

}
