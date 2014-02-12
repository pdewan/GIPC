package port.delay;

import inputport.InputPort;

public class ADelayQueueWithReleaseTimesFactory  implements DelayQueueFactory {
	public DelayQueue createDelayQueue(InputPort anInputPort, String aDestination) {
		return  new ADelayQueueWithReleaseTimes(aDestination);
	}

}
