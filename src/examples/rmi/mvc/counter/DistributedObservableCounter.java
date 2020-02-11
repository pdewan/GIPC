package examples.rmi.mvc.counter;

import java.beans.PropertyChangeListener;
import java.rmi.RemoteException;

import examples.mvc.ObservableCounter;
import examples.rmi.counter.DistributedCounter;

public interface DistributedObservableCounter extends DistributedCounter {
	void addPropertyChangeListener (RemotePropertyChangeListener aListener) throws RemoteException;
}
