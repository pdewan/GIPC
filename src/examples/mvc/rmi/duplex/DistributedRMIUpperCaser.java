package examples.mvc.rmi.duplex;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface DistributedRMIUpperCaser extends Remote {
	String toCountedUpperCase(String string) throws RemoteException;
	void printUpperCase(String string) throws RemoteException;
}
