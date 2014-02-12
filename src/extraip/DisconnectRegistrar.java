package extraip;

import inputport.DisconnectListener;
//methods common to both parties of a connection and names ensure we can use this in a session
public interface DisconnectRegistrar   {	
	// in a socket implementation, accept at the server or connect at client is a connect
//	void  addConnectListener (ConnectListener portConnectListener);
//	void  removeConnectListener (ConnectListener portConnectListener);
//	void addPortReceiveListener(PortReceiveListener portReceiveListener);
//	void removePortReceiveListener(PortReceiveListener portReceiveListener);
	//using disconnect rather than close to be consistent with connect
	void addDisconnectListener(DisconnectListener portCloseListener);
	void removeDisconnectListener(DisconnectListener portCloseListener);
//	void addConnectionListener(ConnectionListener portCloseListener);
//	void removeConnectionListener(ConnectionListener portCloseListener);
//	void notifyPortReceive(String remoteEnd, ByteBuffer message);
//	void notifyConnect(String remoteEnd);
//	void notifyDisconnect(String remoteEnd, boolean eof, String closeReason);
//	void notifyConnectFailure(String remoteEnd, Exception e);

}
