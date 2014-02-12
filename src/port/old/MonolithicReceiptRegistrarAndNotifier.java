package port.old;
import java.nio.ByteBuffer;


public interface MonolithicReceiptRegistrarAndNotifier {
//	void  addPortReceiveListener (String remoteEnd, PortReceiveListener portReceiveListener);
//	void  removePortReceiveListener (String remoteEnd, PortReceiveListener portReceiveListener);
//	void  addPortConnectListener (PortConnectListener portConnectListener);
//	void  removePortConnectListener (PortConnectListener portConnectListener);
	void addReceiveListener(ByteBufferReceiveListener portReceiveListener);
	void removeReceiveListener(ByteBufferReceiveListener portReceiveListener);
//	void addPortCloseListener(PortCloseListener portCloseListener);
//	void removePortCloseListener(PortCloseListener portCloseListener);
	void notifyPortReceive(String remoteEnd, ByteBuffer message, int length);
//	String getCurrentSender();
//	void notifyPortConnect(String remoteEnd);
//	void notifyPortClose(String remoteEnd, boolean eof, String closeReason);
	
}
