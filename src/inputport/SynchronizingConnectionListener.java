package inputport;

import inputport.ConnectionListener;

public interface SynchronizingConnectionListener extends ConnectionListener {
	void waitForConnectionStatus();

}
