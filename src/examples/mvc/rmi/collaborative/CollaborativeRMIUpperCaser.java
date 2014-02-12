package examples.mvc.rmi.collaborative;

import java.rmi.RemoteException;

import examples.mvc.rmi.collaborative.relaying.DistributedRMIEchoer;
import examples.mvc.rmi.muser.MultiUserRMIUpperCaser;

public interface CollaborativeRMIUpperCaser extends MultiUserRMIUpperCaser {
	void connect(String aClientName) throws RemoteException;
}
