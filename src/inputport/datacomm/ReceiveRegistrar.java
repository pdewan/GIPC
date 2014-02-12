package inputport.datacomm;

import java.util.List;

public interface ReceiveRegistrar<MessageType>{
//	void  addPortReceiveListener (String remoteEnd, PortReceiveListener portReceiveListener);
//	void  removePortReceiveListener (String remoteEnd, PortReceiveListener portReceiveListener);
//	void  addPortConnectListener (PortConnectListener portConnectListener);
//	void  removePortConnectListener (PortConnectListener portConnectListener);
	
	
	void addReceiveListener(ReceiveListener<MessageType> portReceiveListener);
	void removeReceiveListener(ReceiveListener<MessageType> portReceiveListener);
	List<ReceiveListener<MessageType>> getReceiveListeners();

//	void addPortCloseListener(PortCloseListener portCloseListener);
//	void removePortCloseListener(PortCloseListener portCloseListener);
//	void notifyPortReceive(String remoteEnd, MessageType message, int length);
//	String getCurrentSender();
//	void notifyPortConnect(String remoteEnd);
//	void notifyPortClose(String remoteEnd, boolean eof, String closeReason);
	
}
