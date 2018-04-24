package inputport.datacomm.simplex.buffer;


import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.List;

import util.trace.Tracer;




public class ASendRegistrarAndNotifier  implements SendRegistrarAndNotifier {

	private List<ByteBufferSendListener> portSendListeners = new ArrayList();

	@Override
	public synchronized void addSendListener(ByteBufferSendListener portSendListener) {	
		Tracer.info(this, "Adding send listener:" + portSendListener);
		if (portSendListeners.contains(portSendListener))
			return;
		portSendListeners.add(portSendListener);		
	}
	@Override
	public synchronized void removeSendListener(ByteBufferSendListener portSendListener) {		
		portSendListeners.remove(portSendListener);		
	}
	
	@Override
	public synchronized void notifyPortSend (String aRemoteEnd, ByteBuffer message, int sendId) {
		Tracer.info(this, "Notifying to send listeners message:" + message + " sendId:" + sendId);
		try {
		for (ByteBufferSendListener portSendListener:portSendListeners)
			portSendListener.messageSent(aRemoteEnd, message, sendId);	
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
	

}
