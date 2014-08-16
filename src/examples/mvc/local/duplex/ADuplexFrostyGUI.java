package examples.mvc.local.duplex;

import java.awt.Component;
import java.beans.PropertyChangeEvent;

import util.awt.TextComponentInterface;
import util.models.PropertyListenerRegistrar;
import examples.mvc.local.simplex.ASimplexFrostyGUI;
import examples.mvc.local.simplex.SimplexFrostyModel;


public abstract class ADuplexFrostyGUI extends ASimplexFrostyGUI  implements DuplexUserInterfaceManager{
	protected TextComponentInterface outputField;
	public void interact (SimplexFrostyModel aModel) {
		super.interact(aModel);
		((PropertyListenerRegistrar) clientModel).addPropertyChangeListener(this);
	}
	protected void createAndAddFrameComponents(){
		super.createAndAddFrameComponents();
		outputField = createTextField("");
		outputField.setEnabled(false);
		frame.add((Component) outputField);
	}
	public void propertyChange(PropertyChangeEvent aPropertyChange) {
		if (aPropertyChange.getPropertyName().equalsIgnoreCase("output"))
			outputField.setText((String) aPropertyChange.getNewValue());
		else if (aPropertyChange.getPropertyName().equalsIgnoreCase("input"))
			inputField.setText((String) aPropertyChange.getNewValue());
	}
}
