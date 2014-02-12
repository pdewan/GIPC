package extraip;

import inputport.ConnectionType;

//methods common to both parties of a connection and names ensure we can use this in a session
public interface DisconnectNotifier {	
//	void notifyConnect(String remoteEnd);
	void notifyDisconnect(String remoteEnd, boolean eof, String closeReason, ConnectionType aConnectionType);
//	void notifyConnectFailure(String remoteEnd, String message);
}
