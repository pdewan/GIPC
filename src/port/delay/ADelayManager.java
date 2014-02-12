package port.delay;

import java.util.HashMap;
import java.util.Map;

import port.common.DistMisc;



public class ADelayManager implements DelayManager {

	Map<String, Integer> destinationToDelay = new HashMap();
	int delayVariation;
	@Override
	public int getMinimumDelay(String name) {
		return destinationToDelay.get(name);
	}
	@Override
	public void setMinimumDelay(String name, int theDelay) {
		destinationToDelay.put(name, theDelay);		
	}
	@Override
	public int getDelayVariation() {
		return delayVariation;
	}
	@Override
	public void setDelayVariation(int theVariation) {
		delayVariation = theVariation;
	}	
	@Override
	public int computeDelay(String destination) {
		Integer minDelay = destinationToDelay.get(destination);	
		if (minDelay == null || minDelay == 0)
			return 0;		
		return DistMisc.random(minDelay, delayVariation);
//		double random = Math.random();
//		long randomDelay = Math.round(delayVariation*random);
//		int actualDelay = (int) randomDelay + minDelay ;
//		return actualDelay;
	}
	
}
