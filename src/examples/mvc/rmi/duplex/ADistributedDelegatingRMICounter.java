package examples.mvc.rmi.duplex;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

import examples.mvc.local.duplex.ACounter;
import examples.mvc.local.duplex.Counter;


public class ADistributedDelegatingRMICounter extends UnicastRemoteObject implements DistributedRMICounter{
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
		if (!(otherObject instanceof DistributedRMICounter))
				return super.equals(otherObject);
		try {
			return getValue().equals(((DistributedRMICounter) otherObject).getValue());
		} catch (RemoteException e) {
			e.printStackTrace();
			return false;
		}
	}	
}
