package examples.nio.manager;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

public class AMeaningOfLifeModel implements MeaningOfLifeModel {
	PropertyChangeSupport propertyChangeSupport = new PropertyChangeSupport(this);
	protected String meaning = "";	
	@Override
	public String getMeaning() {
		return meaning;
	}
	@Override
	public void setMeaning(String newValue) {
		String oldValue = meaning;
		meaning = newValue;
		propertyChangeSupport.firePropertyChange("Meaning", oldValue, newValue);
	}
	@Override
	public void addPropertyChangeListener(PropertyChangeListener aListener) {
		propertyChangeSupport.addPropertyChangeListener(aListener);
		
	}
	

}
