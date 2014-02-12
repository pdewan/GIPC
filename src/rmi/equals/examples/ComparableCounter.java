package rmi.equals.examples;

import java.rmi.RemoteException;

import examples.mvc.rmi.duplex.DistributedRMICounter;


public interface ComparableCounter extends DistributedRMICounter{
	public ComparableCounter greater(ComparableCounter aCounter) throws RemoteException;
}
