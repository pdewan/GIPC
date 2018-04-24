package inputport.datacomm;

import java.util.ArrayList;
import java.util.List;

import util.trace.Tracer;
import util.trace.port.ReceiveListenerNotified;
import util.trace.port.ReceiveListenerRegistered;

public class AReceiveRegistrarAndNotifier<MessageType> implements ReceiveRegistrarAndNotifier<MessageType> {

	private List<ReceiveListener<MessageType>> portReceiveListeners = new ArrayList();

	@Override
	public synchronized void addReceiveListener(ReceiveListener<MessageType> portReceiveListener) {		
		ReceiveListenerRegistered.newCase(this, portReceiveListener);
		Tracer.info(this, "Registering receive listener:" + portReceiveListener);
		if (portReceiveListeners.contains(portReceiveListener))
			return;
		portReceiveListeners.add(portReceiveListener);		
	}
	@Override
	public synchronized void removeReceiveListener(ReceiveListener<MessageType> portReceiveListener) {		
		Tracer.info(this, "Removing receive listener:" + portReceiveListener);
		portReceiveListeners.remove(portReceiveListener);		
	}
	@Override
	public synchronized void notifyPortReceive (String remoteEnd, MessageType message) {
		Tracer.info(this, "Notifying receive listeners");
		try {
		for (ReceiveListener<MessageType> portReceiveListener:portReceiveListeners) {
			ReceiveListenerNotified.newCase(this, portReceiveListener, message);
			portReceiveListener.messageReceived(remoteEnd, message);
		}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Override
	public synchronized List<ReceiveListener<MessageType>> getReceiveListeners() {
		return portReceiveListeners;
	}


}
