package examples.rmi.mvc.counter;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.rmi.RemoteException;

import examples.mvc.counter.ACounterView;
import util.trace.Tracer;

public class ADistributedCounterView extends ACounterView implements RemotePropertyChangeListener{
	DistributedObservableCounter counter;
	public ADistributedCounterView(DistributedObservableCounter aCounter) {
		counter = aCounter;
	}
//	@Override
//	public void propertyChange(PropertyChangeEvent evt) {
//		super.propertyChange(evt);
//		try {
//			Tracer.info(this, "Before call to getValue.");
//			Object aValue = counter.getValue();
//			Tracer.info(this, "getValue returns:" + aValue);
//			
//		} catch (RemoteException e) {
//			e.printStackTrace();
//		}
//	}

}
