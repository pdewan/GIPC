package examples.rmi.counter;

import java.rmi.Remote;
import java.rmi.RemoteException;


public interface DistributedCounter extends Remote {
	void increment(int val) throws RemoteException;
	Object getValue() throws RemoteException;
//	void increment(int val) /*throws RemoteException*/;
//	Object getValue() /*throws RemoteException*/;
//	Object equals(Object otherObject) throws RemoteException;
//	String toString() throws RemoteException;
}
