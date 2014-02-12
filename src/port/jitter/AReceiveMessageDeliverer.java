package port.jitter;

import inputport.datacomm.ReceiveNotifier;
import port.delay.MessageDeliverer;

public class AReceiveMessageDeliverer implements MessageDeliverer {
	ReceiveNotifier<Object> destination;
	public AReceiveMessageDeliverer(ReceiveNotifier aDestination) {
		destination = aDestination;
	}
	@Override
	public void deliver(String aRemoteEnd, Object aMessage) {
		destination.notifyPortReceive(aRemoteEnd, aMessage);
	}
	

}
