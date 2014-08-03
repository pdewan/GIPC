package replicatedserverport.rpc.group.relayed.latecomer.asymmetric.example;

import replicatedserverport.rpc.group.relayed.latecomer.example.ALatecomerReplicatedSessionServer1Launcher;
import replicatedserverport.rpc.group.relayed.latecomer.example.ALatecomerReplicatedSessionServer2Launcher;
import replicatedserverport.rpc.group.relayed.latecomer.example.ALatecomerReplicatedSessionServer3Launcher;
import bus.uigen.pipes.MainClassLaunchingUtility;
/*
 * Single response is implicit. 
 * The asymmetry seems to be in the fact that only Cathy inputs
 * This may be an example to show causality ind elays
 */
public class DemoerOfLatecomerAsymmetricReplicatedGroupRPCPort {
	public static void main(String args[]) {
		demo();
	}
	
	public static void demo() {

		
		Class[] classes = {
				ALatecomerReplicatedSessionServer1Launcher.class,
				ALatecomerReplicatedSessionServer2Launcher.class,
				ALatecomerReplicatedSessionServer3Launcher.class,
				AliceLatecomerAssymetricObjectGroupSessionPortLauncher.class,
				BobLatecomerAssymetricObjectGroupSessionPortLauncher.class,
				CathyInputtingLatecomerAssymetricObjectGroupSessionPortLauncher.class
				
		};
		MainClassLaunchingUtility.createInteractiveLauncher(classes);
	}
	
	

}
