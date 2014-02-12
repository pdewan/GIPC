package replicatedserverport.rpc.group.relayed.latecomer.example;

public class ALatecomerAliceObjectGroupSessionPort {
	public static void main(String[] args) {
//		DelayManager delayManager = GlobalState.getDelayManager();
//		delayManager.setMinimumDelay("Bob", 100);
//		delayManager.setMinimumDelay("Cathy", 5000);
//		AnOldLatecomerObjectGroupSessionPortLauncher.launchSessionPartipant( "9100", "Alice", false, false, false);		
		(new ALatecomerObjectGroupSessionPortLauncher("9100", "Alice")).launch();		

	}


}
