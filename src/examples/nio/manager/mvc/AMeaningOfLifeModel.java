package examples.nio.manager.mvc;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

import util.trace.bean.AddedPropertyChangeListener;
import util.trace.bean.NotifiedPropertyChangeEvent;
import util.trace.bean.SetProperty;

public class AMeaningOfLifeModel implements MeaningOfLifeModel {
	PropertyChangeSupport propertyChangeSupport = new PropertyChangeSupport(this);
	protected String meaning = "";	
	@Override
	public String getMeaning() {
		return meaning;
	}
	@Override
	public void setMeaning(String newValue) {
		SetProperty.newCase(this, "Meaning", newValue);
		String oldValue = meaning;
		meaning = newValue;
		PropertyChangeEvent anEvent = new PropertyChangeEvent(this, "Meaning", oldValue, newValue);
		NotifiedPropertyChangeEvent.newCase(this, anEvent, propertyChangeSupport.getPropertyChangeListeners());
		propertyChangeSupport.firePropertyChange(anEvent);
	}
	@Override
	public void addPropertyChangeListener(PropertyChangeListener aListener) {
		AddedPropertyChangeListener.newCase(this, aListener);
		propertyChangeSupport.addPropertyChangeListener(aListener);
		
	}
	

}
