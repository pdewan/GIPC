package inputport.datacomm.simplex.object;

import inputport.datacomm.ReceiveNotifier;
import util.trace.Tracer;



public class AReceiveObjectForwarder implements ReceiveNotifier<Object> {
	ReceiveNotifier receiveRegistrarAndNotifier;
	public AReceiveObjectForwarder(ReceiveNotifier aReceiveNotifier) {
		receiveRegistrarAndNotifier = aReceiveNotifier;
	}
	@Override
	public void notifyPortReceive(String remoteEnd, Object message) {
		Tracer.info("Forwarding received object:" + message + " from:" + "remoteName");
		receiveRegistrarAndNotifier.notifyPortReceive(remoteEnd, message);		
		
	}

}
