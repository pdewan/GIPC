package examples.mvc.local.duplex;

import java.rmi.RemoteException;

public interface Counter {
	void increment(int val);
	Object getValue();
}
