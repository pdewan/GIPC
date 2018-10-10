package port.old;

import java.nio.ByteBuffer;

import inputport.ConnectListener;
import inputport.ConnectionType;

public interface ListenablePortWithSpecificListeners extends OldInputPort {
	void  addPortReceiveListener (String remoteEnd, ByteBufferReceiveListener portReceiveListener);
	void  removePortReceiveListener (String remoteEnd, ByteBufferReceiveListener portReceiveListener);
	void  addConnectListener (ConnectListener portConnectListener);
	void  removeConnectListener (ConnectListener portConnectListener);
	void addPortReceiveListener(ByteBufferReceiveListener portReceiveListener);
	void notifyPortReceive(String remoteEnd, ByteBuffer message);
	void notifyConnect(String remoteEnd, ConnectionType aConnectionType);
	void notifyDisconnect(String remoteEnd, boolean eof, String closeReason, ConnectionType aConnectionType);
}
