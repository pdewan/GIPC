package inputport;

public interface SynchronizingConnectionListener extends ConnectionListener {
	void waitForConnectionStatus();

}
