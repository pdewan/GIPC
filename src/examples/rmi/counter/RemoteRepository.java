package examples.rmi.counter;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

import examples.mvc.rmi.duplex.DistributedRMICounter;

public interface RemoteRepository extends Remote{
	void deposit(Remote aRemote) throws RemoteException;
	List<Remote> getObjects()  throws RemoteException;
}
