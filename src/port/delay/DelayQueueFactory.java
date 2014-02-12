package port.delay;

import inputport.InputPort;

public interface DelayQueueFactory<MessageType> {
	public DelayQueue<MessageType> 
	    createDelayQueue(InputPort anInputPort, String aDestination);


}
