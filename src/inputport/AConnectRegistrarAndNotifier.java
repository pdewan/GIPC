package inputport;


import java.util.ArrayList;
import java.util.List;

import util.trace.Tracer;




public class AConnectRegistrarAndNotifier implements ConnectionRegistrarAndNotifier {
	List<ConnectionListener> portConnectListeners = new ArrayList();
	@Override
	public void addConnectionListener(ConnectionListener portConnectListener) {
		portConnectListeners.add(portConnectListener);		
	}
	@Override
	public void removeConnectionListener(ConnectionListener portConnectListener) {
		portConnectListeners.remove(portConnectListener);		
	}
	@Override
	public void notifyConnect (String remoteEnd, ConnectionType aConnectionType) {
		Tracer.info(this, "Notifying connect to:" + remoteEnd);
		for (ConnectionListener portConnectListener:portConnectListeners)
			portConnectListener.connected(remoteEnd, aConnectionType);		
	}
	@Override
	public void notifyConnectFailure (String remoteEnd, String message, ConnectionType aConnectionType) {
		for (ConnectionListener portConnectListener:portConnectListeners)
			portConnectListener.notConnected(remoteEnd, message, aConnectionType);		
	}
	@Override
	public void notifyDisconnect (String aRemoteEnd, boolean anExplcitClose, String aCloseReason, ConnectionType aConnectionType) {
		for (ConnectionListener portConnectListener:portConnectListeners)
			portConnectListener.disconnected(aRemoteEnd, anExplcitClose, aCloseReason, aConnectionType);		
	}
}
