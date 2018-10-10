package examples.nio.manager.mvc;

import java.beans.PropertyChangeEvent;
/**
 * Defines a view object that reacts to property changes by outputting the model 
 * property to the console.
 * Can imagine another view that displays them in graphics view.
 * @author Dewan
 *
 */
public class AMeaningOfLifeView implements MeaningOfLifeView{
	@Override
	public void propertyChange(PropertyChangeEvent evt) {
		System.out.println("Meaning of life:" + evt.getNewValue());
	}

}
