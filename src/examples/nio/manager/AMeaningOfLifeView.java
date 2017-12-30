package examples.nio.manager;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

public class AMeaningOfLifeView implements MeaningOfLifeView{
	@Override
	public void propertyChange(PropertyChangeEvent evt) {
		System.out.println("Meaning of life:" + evt.getNewValue());
	}

}
