package examples.rmi.counter;

import java.rmi.Remote;
import java.rmi.RemoteException;


public interface DistributedCounter extends Remote {
	void increment(int val) throws RemoteException;
	Object getValue() throws RemoteException;
}
