package examples.rmi.mvc.counter;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.List;

import examples.rmi.counter.ADistributedObservableCounter;
import util.trace.Tracer;

public class ADistributedRemotelyObservableCounter extends ADistributedObservableCounter implements DistributedObservableCounter{
	protected List<RemotePropertyChangeListener> remoteListeners= new ArrayList<>();

	@Override
	public void addPropertyChangeListener(RemotePropertyChangeListener aListener) throws RemoteException {
		remoteListeners.add(aListener);		
	}
//	@Override
//	public void increment(int anIncrement) {
//		Integer oldValue = value;
//		super.increment(anIncrement);
//		firePropertyChange(new PropertyChangeEvent(this, "Value", oldValue, value));
//		
//	}
	protected void processPropertyChangeEvent(PropertyChangeEvent aPropertyChangeEvent) {
		for (RemotePropertyChangeListener aListener:remoteListeners) {
			try {
				Tracer.info(this, "Before callback to listener:" + aListener);
				aListener.propertyChange(aPropertyChangeEvent);
				Tracer.info(this, "After callback to listener:" + aListener);
			} catch (RemoteException e) {
				e.printStackTrace();
			}
		}
	}
	
}
