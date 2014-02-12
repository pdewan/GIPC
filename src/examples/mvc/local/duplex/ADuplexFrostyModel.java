package examples.mvc.local.duplex;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

import util.annotations.Explanation;
import util.trace.Tracer;
import examples.mvc.local.simplex.ASimplexFrostyModel;

@Explanation("Converts input into upper case + counter")
public class ADuplexFrostyModel extends ASimplexFrostyModel implements DuplexFrostyModel {
	Counter counter;
	protected String output = "";
	protected PropertyChangeSupport propertyChangeSupport = new PropertyChangeSupport(this);
	public ADuplexFrostyModel(DuplexUpperCaser anUpperCaser, 
											Counter aCounter) {
		super(anUpperCaser);
		counter = aCounter;

	}
	public String getInput() {
		return input;
	}
	@Explanation("Input + Counter Value")
	public String getOutput() {
		return output;
	}
	public void setInput(String anInput) {
		counter.increment(1);
		super.setInput(anInput);
		input = "";
		propertyChangeSupport.firePropertyChange("input", anInput, input);	
		processUpperCase(toCountedUpperCase(anInput));
	}
	protected void processUpperCase(String aString) {
		String oldOutput = output;
		output = "You said:" + aString;		
		propertyChangeSupport.firePropertyChange("output", oldOutput, output);	
	}	
	protected String toCountedUpperCase(String anInput ) {	
		return ((DuplexUpperCaser) upperCaser).toCountedUpperCase(anInput);				
	}
	public void addPropertyChangeListener(PropertyChangeListener aListener) {
		Tracer.info(this, "added listener:" + aListener);
		propertyChangeSupport.addPropertyChangeListener(aListener);
	}
	@Override
	public Counter getCounter() {
		return counter;
	}
}
