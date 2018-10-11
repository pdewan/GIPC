package port.old;

import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.List;

import inputport.DisconnectListener;
import inputport.datacomm.simplex.buffer.ByteBufferSendListener;



public class AConnectionSendNotifier extends AConnectionRegistrarAndNotifier implements ConnectionSendNotifier {

	List<ByteBufferSendListener> portSendListeners = new ArrayList();

	@Override
	public void addSendListener(ByteBufferSendListener portSendListener) {		
		if (portSendListeners.contains(portSendListener))
			return;
		portSendListeners.add(portSendListener);		
	}
	@Override
	public void removeSendListener(ByteBufferSendListener portSendListener) {		
		portSendListeners.remove(portSendListener);		
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
	public void notifyPortSend (String aRemoteEnd, ByteBuffer message, int sendId) {
		notifyPortSendListeners(portSendListeners, message, sendId);

	}
	void notifyPortSendListeners (List<ByteBufferSendListener> portSendListeners, ByteBuffer message, int sendId) {
		if (portSendListeners == null) 
			return;
		for (ByteBufferSendListener portSendListener:portSendListeners)
			portSendListener.messageSent(null, message, sendId);		
	}

}
