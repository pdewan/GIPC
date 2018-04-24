package inputport;


import java.util.ArrayList;
import java.util.List;

import util.trace.Tracer;




public class AConnectRegistrarAndNotifier implements ConnectionRegistrarAndNotifier {
	private List<ConnectionListener> portConnectListeners = new ArrayList(); // private so no one else modifies it concurrently
	@Override
	public synchronized void addConnectionListener(ConnectionListener portConnectListener) {
		if (portConnectListeners.contains(portConnectListener)) {
			System.out.println ("Ignoring duplicate connect listnener");
			return;
		}
		portConnectListeners.add(portConnectListener);		
	}
	@Override
	public synchronized void  removeConnectionListener(ConnectionListener portConnectListener) {
		portConnectListeners.remove(portConnectListener);		
	}
	@Override
	public synchronized void notifyConnect (String remoteEnd, ConnectionType aConnectionType) {
		Tracer.info(this, "Notifying connect to:" + remoteEnd);
		for (ConnectionListener portConnectListener:portConnectListeners)
			portConnectListener.connected(remoteEnd, aConnectionType);		
	}
	@Override
	public  synchronized void notifyConnectFailure (String remoteEnd, String message, ConnectionType aConnectionType) {
		for (ConnectionListener portConnectListener:portConnectListeners)
			portConnectListener.notConnected(remoteEnd, message, aConnectionType);		
	}
	@Override
	public synchronized void notifyDisconnect (String aRemoteEnd, boolean anExplcitClose, String aCloseReason, ConnectionType aConnectionType) {
		for (ConnectionListener portConnectListener:portConnectListeners)
			portConnectListener.disconnected(aRemoteEnd, anExplcitClose, aCloseReason, aConnectionType);		
	}
}
