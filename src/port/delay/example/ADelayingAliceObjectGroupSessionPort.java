package port.delay.example;

import port.delay.DelayManager;
import port.delay.DelayUtlity;


public class ADelayingAliceObjectGroupSessionPort {
	public static void main(String[] args) {
		DelayManager delayManager = DelayUtlity.getDelayManager();
		delayManager.setMinimumDelay("Bob", 100);
		delayManager.setMinimumDelay("Cathy", 5000);
		ADelayingObjectGroupSessionPortLauncher.launchSessionPartipant( "9100", "Alice", false, false, false);		
	}


}
