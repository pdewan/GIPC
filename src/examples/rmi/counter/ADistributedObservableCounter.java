package examples.rmi.counter;

import java.rmi.RemoteException;

import examples.counter.ACounter;
import examples.mvc.counter.AnObservableCounter;


public class ADistributedObservableCounter extends AnObservableCounter implements DistributedCounter{
	@Override
	public boolean equals(Object otherObject) {
		if (!(otherObject instanceof DistributedCounter))
				return super.equals(otherObject);
		try {
			return getValue().equals(((DistributedCounter) otherObject).getValue());
		} catch (RemoteException e) {
			e.printStackTrace();
			return false;
		}
	}	
}
