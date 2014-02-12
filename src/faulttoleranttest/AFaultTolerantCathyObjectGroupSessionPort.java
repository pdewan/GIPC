package faulttoleranttest;


import port.delay.DelayManager;
import port.delay.DelayUtlity;

public class AFaultTolerantCathyObjectGroupSessionPort {
	public static void main(String[] args) {
		DelayManager delayManager = DelayUtlity.getDelayManager();
		delayManager.setMinimumDelay("Alice", 5000);
		delayManager.setMinimumDelay("Bob", 100);
		AFaultTolerantSessionPortLauncher.launchSessionPartipant( "9102", "Cathy", false, false, true);		
	}
}
