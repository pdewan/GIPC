package examples.mvc.counter;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import util.trace.Tracer;

public class ACounterView implements PropertyChangeListener{
	@Override
	public void propertyChange(PropertyChangeEvent evt) {
		Tracer.info(this, evt.toString());
	}
}
