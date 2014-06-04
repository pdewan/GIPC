package examples.rmi.arithmetic;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface RemoteMixedTypeProcessor extends Remote  {
	int intAdd (int num1, double num2) throws RemoteException;
	double doubleAdd (int num1, double num2) throws RemoteException;
	int intMultiply (int num1, double num2) throws RemoteException;
	double doubleMultiply (int num1, double num2) throws RemoteException;

}
