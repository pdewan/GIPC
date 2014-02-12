package port.delay.example;

import port.delay.DelayManager;
import port.delay.DelayUtlity;


public class ADelayingBobObjectGroupSessionPort {
	public static void main(String[] args) {
		DelayManager delayManager = DelayUtlity.getDelayManager();
		delayManager.setMinimumDelay("Alice", 100);
		delayManager.setMinimumDelay("Cathy", 100);
		ADelayingObjectGroupSessionPortLauncher.launchSessionPartipant("9101", "Bob", false, true, false);		
	}


}
