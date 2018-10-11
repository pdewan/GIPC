package examples.mvc.local.duplex;

import java.beans.PropertyChangeEvent;

import examples.mvc.local.simplex.ASimplexFrostyConsoleUI;
import examples.mvc.local.simplex.SimplexFrostyModel;
import util.models.PropertyListenerRegisterer;
import util.trace.Tracer;

public class ADuplexFrostyConsoleUI extends ASimplexFrostyConsoleUI implements DuplexUserInterfaceManager  {
//	public ADuplexFrostyConsoleUI(DuplexFrostyModel aModel) {
//		super(aModel);
//		((PropertyListenerRegisterer) clientModel).addPropertyChangeListener(this);
//	}
	public void interact (SimplexFrostyModel aModel) {
		((PropertyListenerRegisterer) aModel).addPropertyChangeListener(this);
		super.interact(aModel);
	}
	@Override
	public void propertyChange(PropertyChangeEvent aPropertyChange) {
		Tracer.info(this, "message Received:" + aPropertyChange.getPropertyName());
		if (aPropertyChange.getPropertyName().equalsIgnoreCase("output"))
			System.out.println(aPropertyChange.getNewValue());
	}
}
