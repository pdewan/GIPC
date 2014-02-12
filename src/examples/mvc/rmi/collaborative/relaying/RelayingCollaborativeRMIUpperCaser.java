package examples.mvc.rmi.collaborative.relaying;

import java.rmi.RemoteException;

import examples.mvc.rmi.collaborative.CollaborativeRMIUpperCaser;

public interface RelayingCollaborativeRMIUpperCaser extends CollaborativeRMIUpperCaser {
	 void addListener(String aName, DistributedRMIEchoer anEchoer) throws RemoteException;
	 void relayToOthers(String aString, String aCallerName) throws RemoteException;
}
