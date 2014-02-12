package replicatedserverport.rpc.group.relayed.latecomer.asymmetric.example;

public class ALatecomerAssymetricAliceObjectGroupSessionPort {
	public static void main(String[] args) {
//		DelayManager delayManager = GlobalState.getDelayManager();
//		delayManager.setMinimumDelay("Bob", 100);
//		delayManager.setMinimumDelay("Cathy", 5000);
//		AnOldLatecomerObjectGroupSessionPortLauncher.launchSessionPartipant( "9100", "Alice", false, false, false);		
		(new ALatecomerAssymetricObjectGroupSessionPortLauncher("9100", "Alice", false, false, false)).launch();		

	}


}
