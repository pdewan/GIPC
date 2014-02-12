package faulttoleranttest;


import port.delay.DelayManager;
import port.delay.DelayUtlity;

public class AFaultTolerantBobObjectGroupSessionPort {
	public static void main(String[] args) {
		DelayManager delayManager = DelayUtlity.getDelayManager();
		delayManager.setMinimumDelay("Alice", 100);
		delayManager.setMinimumDelay("Cathy", 100);
		AFaultTolerantSessionPortLauncher.launchSessionPartipant("9101", "Bob", false, true, false);		
	}


}
