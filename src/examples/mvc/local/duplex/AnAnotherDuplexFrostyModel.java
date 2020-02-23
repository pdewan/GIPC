package examples.mvc.local.duplex;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

import examples.counter.Counter;
import examples.mvc.local.simplex.ASimplexFrostyModel;
import examples.mvc.rmi.collaborative.relaying.Echoer;
import util.models.PropertyListenerRegisterer;


public class AnAnotherDuplexFrostyModel extends ASimplexFrostyModel implements AnotherDuplexFrostyModel {
	Echoer echoer;
	Counter counter;
//	DuplexUpperCaser upperCaser;
	String echo;
//	protected String input = "";
	PropertyChangeSupport propertyChangeSupport = new PropertyChangeSupport(this);
	public AnAnotherDuplexFrostyModel(AnotherDuplexUpperCaser anUpperCaser, 
											Echoer anEchoer, 
											Counter aCounter) {
		super(anUpperCaser);
		echoer = anEchoer;
		counter = aCounter;
//		upperCaser = anUpperCaser;
		((PropertyListenerRegisterer) echoer).addPropertyChangeListener(this);
	}
//	public AnAnotherDuplexFrostyModel() {
//		((PropertyListenerRegisterer) echoer).addPropertyChangeListener(this);
//
//    }
	
//	public String getInput() {
//		return input;
//	}
	
//	protected void toUpperCase(String anInput ) {		
//		upperCaser.toUpperCase(anInput);				
//	}
	
	public String getOutput() {
		return echo;
	}
	public void setInput(String anInput) {
		counter.increment(1);
		super.setInput(anInput);
		echoUpperCase(anInput);
		propertyChangeSupport.firePropertyChange("input", anInput, input);	
	}
	
	protected void echoUpperCase(String anInput ) {	
//		upperCaser.printUpperCase(anInput);
		((DuplexUpperCaser) upperCaser).toCountedUpperCase(anInput);				
	}

	@Override
	public void addPropertyChangeListener(PropertyChangeListener aListener) {
		propertyChangeSupport.addPropertyChangeListener(aListener);
	}

	@Override
	public void propertyChange(PropertyChangeEvent evt) {
		echo = (String) evt.getNewValue();
		propertyChangeSupport.firePropertyChange(evt);
	}

}
