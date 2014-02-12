package faulttoleranttest;


import port.delay.DelayManager;
import port.delay.DelayUtlity;

public class AFaultTolerantAliceObjectGroupSessionPort {
	public static void main(String[] args) {
		DelayManager delayManager = DelayUtlity.getDelayManager();
		delayManager.setMinimumDelay("Bob", 100);
		delayManager.setMinimumDelay("Cathy", 5000);
		AFaultTolerantSessionPortLauncher.launchSessionPartipant( "9100", "Alice", false, false, false);		
	}


}
