package examples.rmi.arithmetic.stateful;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface RemoteStatefulMixedTypeProcessor extends Remote  {
	void setInt(int num1) throws RemoteException;
	void setDouble(double num2) throws RemoteException;	
	int intAdd() throws RemoteException;
	double doubleAdd () throws RemoteException;
	int intMultiply () throws RemoteException;
	double doubleMultiply () throws RemoteException;

}
