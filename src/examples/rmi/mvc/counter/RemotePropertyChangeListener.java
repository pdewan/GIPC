package examples.rmi.mvc.counter;

import java.beans.PropertyChangeEvent;
import java.rmi.Remote;
import java.rmi.RemoteException;


public interface RemotePropertyChangeListener extends Remote {
	public void propertyChange(PropertyChangeEvent evt) throws RemoteException;
}
