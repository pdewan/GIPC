package inputport;

public interface DisconnectNotifier {
	void notifyDisconnect(String aRemoteEnd, boolean anExplcitClose, String anExplanation, ConnectionType aConnectionType);

}
