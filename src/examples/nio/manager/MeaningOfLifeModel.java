package examples.nio.manager;

import util.models.PropertyListenerRegisterer;

public interface MeaningOfLifeModel extends PropertyListenerRegisterer{
	String getMeaning();
	void setMeaning(String newValue);
}
