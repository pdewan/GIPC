package inputport.datacomm.simplex.buffer;


import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.List;

import util.trace.Tracer;




public class ASendRegistrarAndNotifier  implements SendRegistrarAndNotifier {

	List<ByteBufferSendListener> portSendListeners = new ArrayList();

	@Override
	public void addSendListener(ByteBufferSendListener portSendListener) {	
		Tracer.info(this, "Adding send listener:" + portSendListener);
		if (portSendListeners.contains(portSendListener))
			return;
		portSendListeners.add(portSendListener);		
	}
	@Override
	public void removeSendListener(ByteBufferSendListener portSendListener) {		
		portSendListeners.remove(portSendListener);		
	}
	
	@Override
	public void notifyPortSend (String aRemoteEnd, ByteBuffer message, int sendId) {
		Tracer.info(this, "Notifying to send listeners message:" + message + " sendId:" + sendId);
		for (ByteBufferSendListener portSendListener:portSendListeners)
			portSendListener.messageSent(aRemoteEnd, message, sendId);	

	}
	

}
