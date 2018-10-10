package replicatedsessionport.rpc.duplex.singleresponse.example;

import bus.uigen.pipe.MainClassLaunchingUtility;
/*
 * This allows the servervs to be replicatd of course but to also dynamically come and go
 * using the latecomer server. They must join the session as servers and their clients as clients (only)
 * buffered messages are replaye din both servers and clients
 * start server 1 and 2, start alice, say something, start bob, say someything, kill 1, say something, kill 2
 * say someyhing, start 3, sat something, start alice, pretty cool,no messages missed
 */
public class DemoerOfReplicatedSessionPort {
	public static void main(String args[]) {
		demo();


	}
	
	public static void demo() {

		Class[] classes = {
				ALatecomerSessionServerLauncher.class,
				AServer1SingleResponseGroupSessionServerPortLauncher.class,
				AServer2SingleResponseGroupSessionServerPortLauncher.class,
				AliceSingleResponseReplicatedSessionClientPortLauncher.class,
				BobSingleResponseReplicatedSessionClientPortLauncher.class,
				CathySingleResponseReplicatedSessionClientPortLauncher.class,
				AServer3SingleResponseGroupSessionServerPortLauncher.class, // start after killing 1 and 2
			
		};
		MainClassLaunchingUtility.createInteractiveLauncher(classes);

	}
	
	

}
