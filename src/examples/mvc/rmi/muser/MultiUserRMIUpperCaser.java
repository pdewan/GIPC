package examples.mvc.rmi.muser;

import java.rmi.Remote;
import java.rmi.RemoteException;

import examples.mvc.rmi.collaborative.relaying.DistributedRMIEchoer;
import examples.mvc.rmi.duplex.DistributedRMIUpperCaser;

public interface MultiUserRMIUpperCaser extends DistributedRMIUpperCaser {
	String toCountedUpperCase(String string, String aUserName) throws RemoteException;
//	void printUpperCase(String string) throws RemoteException;
}
