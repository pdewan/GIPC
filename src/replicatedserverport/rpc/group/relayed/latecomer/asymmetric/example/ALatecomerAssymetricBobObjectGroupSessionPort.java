package replicatedserverport.rpc.group.relayed.latecomer.asymmetric.example;

public class ALatecomerAssymetricBobObjectGroupSessionPort {
	public static void main(String[] args) {
//		DelayManager delayManager = GlobalState.getDelayManager();
//		delayManager.setMinimumDelay("Alice", 100);
//		delayManager.setMinimumDelay("Cathy", 100);
//		AnOldLatecomerObjectGroupSessionPortLauncher.launchSessionPartipant("9101", "Bob", false, true, false);		
		(new ALatecomerAssymetricObjectGroupSessionPortLauncher("9101", "Bob", false, true, false)).launch();		

	}


}
