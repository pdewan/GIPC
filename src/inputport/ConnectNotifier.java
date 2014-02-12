package inputport;

public interface ConnectNotifier {
	void notifyConnect(String aRemoteEnd, ConnectionType aConnectionType);
	void notifyConnectFailure(String aRemoteEnd, String anExplanation, ConnectionType aConnectionType);
}
