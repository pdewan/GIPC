package examples.mvc;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

import examples.mvc.local.duplex.ACounter;

public class AnObservableCounter extends ACounter implements ObservableCounter {
	protected PropertyChangeSupport propertyChangeSupport = new PropertyChangeSupport(this);

	@Override
	public void addPropertyChangeListener(PropertyChangeListener aListener) {
		propertyChangeSupport.addPropertyChangeListener(aListener);
		
	}
	protected void processPropertyChangeEvent(PropertyChangeEvent aPropertyChangeEvent) {
		propertyChangeSupport.firePropertyChange(aPropertyChangeEvent);
	}
	@Override
	public void increment(int anIncrement) {
		Integer oldValue = value;
		super.increment(anIncrement);
		processPropertyChangeEvent( 
		  new PropertyChangeEvent(this, "Value", oldValue, value));
		
		
	}

}
