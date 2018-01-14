package inputport.datacomm;

import java.util.ArrayList;
import java.util.List;

import util.trace.Tracer;
import util.trace.port.ReceiveListenerNotified;
import util.trace.port.ReceiveListenerRegistered;

public class AReceiveRegistrarAndNotifier<MessageType> implements ReceiveRegistrarAndNotifier<MessageType> {

	protected List<ReceiveListener<MessageType>> portReceiveListeners = new ArrayList();

	@Override
	public void addReceiveListener(ReceiveListener<MessageType> portReceiveListener) {		
		ReceiveListenerRegistered.newCase(this, portReceiveListener);
		Tracer.info(this, "Registering receive listener:" + portReceiveListener);
		if (portReceiveListeners.contains(portReceiveListener))
			return;
		portReceiveListeners.add(portReceiveListener);		
	}
	@Override
	public void removeReceiveListener(ReceiveListener<MessageType> portReceiveListener) {		
		Tracer.info(this, "Removing receive listener:" + portReceiveListener);
		portReceiveListeners.remove(portReceiveListener);		
	}
	@Override
	public void notifyPortReceive (String remoteEnd, MessageType message) {
		Tracer.info(this, "Notifying receive listeners");
		for (ReceiveListener<MessageType> portReceiveListener:portReceiveListeners) {
			ReceiveListenerNotified.newCase(this, portReceiveListener, message);
			portReceiveListener.messageReceived(remoteEnd, message);
		}
	}
	
	@Override
	public List<ReceiveListener<MessageType>> getReceiveListeners() {
		return portReceiveListeners;
	}


}
