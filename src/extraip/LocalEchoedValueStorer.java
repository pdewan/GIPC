package extraip;

import java.rmi.RemoteException;

public interface LocalEchoedValueStorer {
	public String getEcho() throws RemoteException;

}
