package replicatedserverport.rpc.group.relayed.latecomer.example;

public class CathyLatecomerObjectGroupSessionPortLauncher {
	public static void main(String[] args) {
//		DelayManager delayManager = GlobalState.getDelayManager();
//		delayManager.setMinimumDelay("Alice", 5000);
//		delayManager.setMinimumDelay("Bob", 100);
//		AnOldLatecomerObjectGroupSessionPortLauncher.launchSessionPartipant( "9102", "Cathy", false, false, true);		
		(new ALatecomerObjectGroupSessionPortLauncher("9102", "Cathy")).launch();		

	}
}
