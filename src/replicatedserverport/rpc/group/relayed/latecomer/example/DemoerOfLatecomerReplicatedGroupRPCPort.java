package replicatedserverport.rpc.group.relayed.latecomer.example;

import bus.uigen.pipe.MainClassLaunchingUtility;
/*
 * This seems to be the same as single response, the rsponse is not qualified
 * Itis an older piece of code that had to be fixed
 */
public class DemoerOfLatecomerReplicatedGroupRPCPort {
	public static void main(String args[]) {
		demo();
	}
	
	public static void demo() {

		
		Class[] classes = {
				ALatecomerReplicatedSessionServer1Launcher.class,
				ALatecomerReplicatedSessionServer2Launcher.class,
				ALatecomerReplicatedSessionServer3Launcher.class,
				AliceLatecomerObjectGroupSessionPortLauncher.class,
				BobLatecomerObjectGroupSessionPortLauncher.class,
				CathyLatecomerObjectGroupSessionPortLauncher.class
				
		};
		MainClassLaunchingUtility.createInteractiveLauncher(classes);
	}
	
	

}
