package port.delay.example;

import port.delay.DelayManager;
import port.delay.DelayUtlity;


public class ADelayingCathyObjectGroupSessionPort {
	public static void main(String[] args) {
		DelayManager delayManager = DelayUtlity.getDelayManager();
		delayManager.setMinimumDelay("Alice", 5000);
		delayManager.setMinimumDelay("Bob", 100);
		ADelayingObjectGroupSessionPortLauncher.launchSessionPartipant( "9102", "Cathy", false, false, true);		
	}
}
