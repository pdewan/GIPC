package replicatedserverport.rpc.group.relayed.latecomer.asymmetric.example;

public class ALatecomerAssymetricCathyObjectGroupSessionPort {
	public static void main(String[] args) {
//		DelayManager delayManager = GlobalState.getDelayManager();
//		delayManager.setMinimumDelay("Alice", 5000);
//		delayManager.setMinimumDelay("Bob", 100);
//		AnOldLatecomerObjectGroupSessionPortLauncher.launchSessionPartipant( "9102", "Cathy", false, false, true);		
		(new ALatecomerAssymetricObjectGroupSessionPortLauncher("9102", "Cathy", false, false, true)).launch();		

	}
}
