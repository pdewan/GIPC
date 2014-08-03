package replicatedserverport.rpc.group.earliestresponse.relayed.latecomer.example;

import replicatedserverport.rpc.group.earliestresponse.relayed.latecomer.example.AnEarliestResponseReplicatedLatecomerSessionServer3Launcher;
import bus.uigen.pipe.MainClassLaunchingUtility;
/* the number of servers can vary from 1 to 3 but they
 * all have to be started before interaction. All of them compute
 * the RPC calls and return results, but only one of the results is accepted.
 * however, there is fault tolerance, as long as there is a server the
 * dialogue goes on. The server failure is communicated to clients as exeptions 
 * since this is not P2P, the interaction does  stop when all severs go away
 * however, there is latecomer support so as long as there is one serer around
 * a client can join
 */
public class DemoerOfLatecomertReplicatedGroupRPCEarliestResponsePort {
	public static void main(String args[]) {
		demo();
	}
	
	public static void demo() {

		
		Class[] classes = {
				AnEarliestResponseReplicatedLatecomerSessionServer1Launcher.class,
				AnEarliestResponseReplicatedLatecomerSessionServer2Launcher.class,
				AnEarliestResponseReplicatedLatecomerSessionServer3Launcher.class,
				AliceEarliestResponseLatecomerReplicatedPortLauncher.class,
				BobEarliestResponseLatecomerReplicatedPortLauncher.class,
				CathyEarliestResponseLatecomerReplicatedPortLauncher.class
				
		};
		MainClassLaunchingUtility.createInteractiveLauncher(classes);
	}
	
	

}
