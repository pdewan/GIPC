package port.old;

import inputport.ConnectListener;
import inputport.ConnectionListener;
import inputport.ConnectionType;

import java.util.ArrayList;
import java.util.List;

//It may be a session notifying, so do not call it ConnectionNotifyingPort
public class AConnectionRegistrarAndNotifier extends ADisconnectRegistrarAndNotifier implements PureConnectRegistrarAndNotifier {

	List<ConnectListener> portConnectListeners = new ArrayList();
//	List<DisconnectListener> portCloseListeners = new ArrayList();

//	@Override
//	public void addDisconnectListener(DisconnectListener portCloseListener) {		
//		if (portCloseListeners.contains(portCloseListener))
//			return;
//		portCloseListeners.add(portCloseListener);		
//	}
//	@Override
//	public void removeDisconnectListener(DisconnectListener portCloseListener) {		
//		portCloseListeners.remove(portCloseListener);		
//	}
//
//
//	@Override
//	public void notifyDisconnect (String remoteEnd, boolean eof, String closeReason) {
//		notifyPortCloseListeners(portCloseListeners, remoteEnd, eof, closeReason);
//
//	}
//	void notifyPortCloseListeners (List<DisconnectListener> portCloseListeners, String remoteEnd, boolean eof, String closeReason) {
//		if (portCloseListeners == null) 
//			return;
//		for (DisconnectListener portCloseListener:portCloseListeners)
//			portCloseListener.disconnected(remoteEnd, eof, closeReason);		
//	}

	@Override
	public void addConnectionListener(ConnectionListener portConnectListener) {

		if (portConnectListeners.contains(portConnectListener))
			return;
		portConnectListeners.add(portConnectListener);
		
	}

	@Override
	public void removeConnectionListener(ConnectionListener portConnectListener) {

		portConnectListeners.remove(portConnectListener);	
		
	}

	@Override
	public void notifyConnect (String remoteEnd, ConnectionType aConnectionType) {
		//List<PortConnectListener> portConnectListeners = remoteEndToPortConnectListeners.get(remoteEnd);
		if (portConnectListeners == null)
			return;
		for (ConnectListener portConnectListener:portConnectListeners)
			portConnectListener.connected(remoteEnd, null);
		
	}
	@Override
	public void notifyConnectFailure (String remoteEnd, String message, ConnectionType aConnectionType) {
		//List<PortConnectListener> portConnectListeners = remoteEndToPortConnectListeners.get(remoteEnd);
		if (portConnectListeners == null)
			return;
		for (ConnectListener portConnectListener:portConnectListeners)
			portConnectListener.notConnected(remoteEnd, message, null);
		
	}

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
