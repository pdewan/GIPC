package rmi.equals.examples;

import java.rmi.RemoteException;

import examples.mvc.rmi.duplex.ADistributedDelegatingRMICounter;


public class AComparableCounter extends ADistributedDelegatingRMICounter implements ComparableCounter {
	protected AComparableCounter() throws RemoteException {
		super();
	}
	public ComparableCounter greater(ComparableCounter aCounter) {
		try {
			if ((Integer) getValue() <(Integer) aCounter.getValue()) {
				return aCounter;
			}
			else return this;
		} catch (RemoteException e) {
			e.printStackTrace();
			return this;
		}
	}
}
