package examples.rmi.counter.repository;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

public interface RemoteRepository extends Remote{
	void deposit(Remote aRemote) throws RemoteException;
	List<Remote> getObjects()  throws RemoteException;
}
