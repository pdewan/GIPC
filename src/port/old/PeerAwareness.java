package port.old;

import inputport.ConnectListener;
import inputport.DisconnectListener;
// strictly needed for group communication, but let us do this in duplex input port for now as then we can be independent of nio, rpc, etc
public interface PeerAwareness {
	String[] getPeerNames();
	void  addPeerConnectListener (ConnectListener portConnectListener);
	void  removePeerConnectListener (ConnectListener portConnectListener);
	void addPeerDisconnectListener(DisconnectListener portCloseListener);
	void removePeerDisconnectListener(DisconnectListener portCloseListener);
	void notifyPeerConnect(String remoteEnd);
	void notifyPeerDisconnect(String remoteEnd, boolean eof, String closeReason);

}
