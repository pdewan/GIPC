package rmi.equals.examples;

import java.rmi.RemoteException;

import examples.rmi.counter.DistributedCounter;


public interface ComparableCounter extends DistributedCounter{
	public ComparableCounter greater(ComparableCounter aCounter) throws RemoteException;
}
