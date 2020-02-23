package examples.mvc.rmi.duplex;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

import examples.counter.ACounter;
import examples.counter.Counter;
import examples.rmi.counter.DistributedCounter;


public class ADistributedDelegatingRMICounter extends UnicastRemoteObject implements DistributedCounter{
	public ADistributedDelegatingRMICounter() throws RemoteException {
		super();
	}
	Counter counter = new ACounter();	
	public Object getValue() {
		return counter.getValue();
	}	
	public void increment(int val) {
		counter.increment(val);
	}	
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
