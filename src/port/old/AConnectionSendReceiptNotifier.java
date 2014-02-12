package port.old;


import inputport.DisconnectListener;

import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.List;



public class AConnectionSendReceiptNotifier extends AConnectionSendNotifier implements ConnectionSendReceiptNotifier {

	List<ByteBufferReceiveListener> portReceiveListeners = new ArrayList();

	@Override
	public void addReceiveListener(ByteBufferReceiveListener portReceiveListener) {		
		if (portReceiveListeners.contains(portReceiveListener))
			return;
		portReceiveListeners.add(portReceiveListener);		
	}
	@Override
	public void removeReceiveListener(ByteBufferReceiveListener portReceiveListener) {		
		portReceiveListeners.remove(portReceiveListener);		
	}
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
	public void notifyPortReceive (String remoteEnd, ByteBuffer message, int length) {
		notifyPortReceiveListeners(portReceiveListeners, remoteEnd, message, length);
//		List<PortReceiveListener> specificPortReceiveListeners = remoteEndToPortReceiveListeners.get(remoteEnd);
//		notifyPortReceiveListeners(specificPortReceiveListeners, remoteEnd, message);
	}
	void notifyPortReceiveListeners (List<ByteBufferReceiveListener> portReceiveListeners,  String remoteEnd, ByteBuffer message, int length) {
		if (portReceiveListeners == null) 
			return;
		for (ByteBufferReceiveListener portReceiveListener:portReceiveListeners)
			portReceiveListener.messageReceived(remoteEnd, message, length);		
	}

}
