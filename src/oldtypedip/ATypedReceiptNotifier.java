package oldtypedip;


import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import port.old.AConnectionSendReceiptNotifier;

public  class ATypedReceiptNotifier extends AConnectionSendReceiptNotifier implements TypedReceiptNotifier{
	List<TypedReceiveListener> typedPortReceiveListeners = new ArrayList();
	@Override
	public void addTypedReceiveListener(TypedReceiveListener portReceiveListener) {
		if (typedPortReceiveListeners.contains(portReceiveListener))
			return;
		typedPortReceiveListeners.add(portReceiveListener);	
		
	}

	@Override
	public void notifyTypedPortReceive(String remoteEnd, Serializable message) {
		for (TypedReceiveListener portReceiveListener:typedPortReceiveListeners)
			portReceiveListener.messageReceived(remoteEnd, message);
		
	}

	@Override
	public void removeTypedReceiveListener(
			TypedReceiveListener portReceiveListener) {
		typedPortReceiveListeners.remove(portReceiveListener);	
	}

}
