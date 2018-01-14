package examples.nio.manager.mvc;

import util.models.PropertyListenerRegisterer;

public interface MeaningOfLifeModel extends PropertyListenerRegisterer{
	String getMeaning();
	void setMeaning(String newValue);
}
