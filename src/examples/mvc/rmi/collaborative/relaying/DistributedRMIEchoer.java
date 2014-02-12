package examples.mvc.rmi.collaborative.relaying;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface DistributedRMIEchoer extends Remote {
	void echo(String value) throws RemoteException;
}
