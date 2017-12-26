package trace.port;


public class ConnectionEventManagerFactory {
	static ConnectionEventListener connectionManager;
	public static ConnectionEventListener getConnectionManager() {
		if (connectionManager == null) {
			connectionManager = new AConnectionEventManager();
			ConnectiontEventBus.addConnectionEventListener(connectionManager);
		}
		return connectionManager;
	}

}
