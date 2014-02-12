package port.jitter;

import inputport.InputPort;
import port.delay.ADelayQueueWithReleaseTimesFactory;
import port.delay.DelayQueue;
import port.delay.DelayQueueFactory;
import util.misc.HashIdentityMap;
import util.misc.IdentityMap;

public class JitterDelayQueueSelector {
	static IdentityMap<String, DelayQueue> destinationToDelayQueue = new HashIdentityMap();
	static DelayQueueFactory factory = new ADelayQueueWithReleaseTimesFactory();
	
	public static DelayQueue createDelayQueue(InputPort anInputPort, String aDestination) {
		DelayQueue delayQueue =  factory.createDelayQueue(anInputPort, aDestination);
		destinationToDelayQueue.put(aDestination, delayQueue);
		return delayQueue;
	}
	public static DelayQueueFactory getDelayQueueFactory(DelayQueueFactory aFactory) {
		return factory;
	}
	public static void setDelayQueueFactory(DelayQueueFactory aFactory) {
		factory = aFactory;
	}
	
	public static DelayQueue getDelayQueue (String aDestination) {
		return destinationToDelayQueue.get(aDestination);
	}
	

}
