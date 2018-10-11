package port.old;

import java.util.ArrayList;
import java.util.List;

import extraip.DisconnectRegistrarAndNotifier;
import inputport.ConnectionType;
import inputport.DisconnectListener;

//It may be a session notifying, so do not call it ConnectionNotifyingPort
public class ADisconnectRegistrarAndNotifier implements DisconnectRegistrarAndNotifier {

//	List<ConnectListener> portConnectListeners = new ArrayList();
	protected List<DisconnectListener> portCloseListeners = new ArrayList();

	@Override
	public void addDisconnectListener(DisconnectListener portCloseListener) {		
		if (portCloseListeners.contains(portCloseListener))
			return;
		portCloseListeners.add(portCloseListener);		
	}
	@Override
	public void removeDisconnectListener(DisconnectListener portCloseListener) {		
		portCloseListeners.remove(portCloseListener);		
	}


	@Override
	public void notifyDisconnect (String remoteEnd, boolean eof, String closeReason, ConnectionType aConnectionType) {
		notifyPortCloseListeners(portCloseListeners, remoteEnd, eof, closeReason);

	}
	void notifyPortCloseListeners (List<DisconnectListener> portCloseListeners, String remoteEnd, boolean eof, String closeReason) {
		if (portCloseListeners == null) 
			return;
		for (DisconnectListener portCloseListener:portCloseListeners)
			portCloseListener.disconnected(remoteEnd, eof, closeReason, null);		
	}

//	@Override
//	public void addConnectListener(ConnectListener portConnectListener) {
//
//		if (portConnectListeners.contains(portConnectListener))
//			return;
//		portConnectListeners.add(portConnectListener);
//		
//	}
//
//	@Override
//	public void removeConnectListener(ConnectListener portConnectListener) {
//
//		portConnectListeners.remove(portConnectListener);	
//		
//	}
//
//	@Override
//	public void notifyConnect (String remoteEnd) {
//		//List<PortConnectListener> portConnectListeners = remoteEndToPortConnectListeners.get(remoteEnd);
//		if (portConnectListeners == null)
//			return;
//		for (ConnectListener portConnectListener:portConnectListeners)
//			portConnectListener.connected(remoteEnd);
//		
//	}
//	@Override
//	public void notifyConnectFailure (String remoteEnd, String message) {
//		//List<PortConnectListener> portConnectListeners = remoteEndToPortConnectListeners.get(remoteEnd);
//		if (portConnectListeners == null)
//			return;
//		for (ConnectListener portConnectListener:portConnectListeners)
//			portConnectListener.notConnected(remoteEnd, message);
//		
//	}
//
//	@Override
//	public void addConnectionListener(ConnectionListener theConnectionListener) {
//		addConnectListener(theConnectionListener);
//		addDisconnectListener(theConnectionListener);
//	}	
//	@Override
//	public void removeConnectionListener(ConnectionListener theConnectionListener) {
//		removeConnectListener(theConnectionListener);
//		removeDisconnectListener(theConnectionListener);		
//	}

}
