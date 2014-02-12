package examples.mvc.local.duplex;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.Frame;
import java.awt.GridLayout;
import java.awt.Label;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;

import examples.mvc.local.simplex.ASimplexFrostyGUI;
import examples.mvc.local.simplex.SimplexFrostyModel;


import util.awt.ATextField;
import util.awt.TextComponentInterface;
import util.models.PropertyListenerRegisterer;


public abstract class ADuplexFrostyGUI extends ASimplexFrostyGUI  implements DuplexUserInterfaceManager{
	protected TextComponentInterface outputField;
	public void interact (SimplexFrostyModel aModel) {
		super.interact(aModel);
		((PropertyListenerRegisterer) clientModel).addPropertyChangeListener(this);
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
