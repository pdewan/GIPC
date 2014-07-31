package examples.rmi.arithmetic.stateful;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface RemoteStatefulMixedTypeProcessor extends Remote  {
	void setInt(Integer num1) throws RemoteException;
	void setDouble(Double num2) throws RemoteException;	
	Integer intAdd() throws RemoteException;
	Double doubleAdd () throws RemoteException;
	Integer intMultiply () throws RemoteException;
	Double doubleMultiply () throws RemoteException;

}
