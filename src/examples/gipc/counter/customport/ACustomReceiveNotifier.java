package examples.gipc.counter.customport;

import util.trace.Tracer;
import inputport.datacomm.AReceiveRegistrarAndNotifier;
import inputport.datacomm.ReceiveListener;

public class ACustomReceiveNotifier extends AReceiveRegistrarAndNotifier<Object>{
	@Override
	public void notifyPortReceive (String remoteEnd, Object message) {
		if (message instanceof TimeStampedMessage) {
			System.out.println ("Unwrapping:" + message);
			message = ((TimeStampedMessage) message).getMessage();
		}
		Tracer.info(this, "Notifying receive listeners");
		for (ReceiveListener portReceiveListener:portReceiveListeners) {
			portReceiveListener.messageReceived(remoteEnd, message);
		}
	}

}
