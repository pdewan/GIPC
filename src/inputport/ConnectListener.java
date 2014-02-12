package inputport;

public interface ConnectListener {
	public void connected(String aRemoteEndName, ConnectionType aConnectionType);
	public void notConnected(String aRemoteEndName, String anExplanation, ConnectionType aConnectionType);
}
