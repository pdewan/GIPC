package examples.mvc.rmi.duplex;

import java.io.Serializable;
import java.rmi.Remote;
import java.rmi.RemoteException;


public interface DistributedRMICounter extends Remote {
	void increment(int val) throws RemoteException;
	Object getValue() throws RemoteException;
}
