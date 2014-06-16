package replicatedserverport.rpc.group.earliestresponse.direct.example;

import replicatedserverport.rpc.group.earliestresponse.relayed.latecomer.example.AnEarliestResponseReplicatedLatecomerSessionServer3Launcher;
import bus.uigen.models.MainClassLaunchingUtility;
/* the number of servers can vary from 1 to 3 but they
 * all have to be started before interaction. All of them compute
 * the RPC calls and return results, but only one of the results is accepted.
 * however, there is fault tolerance, as long as there is a server the
 * dialogue goes on. The server failure is communicated to clients as exeptions 
 * since this is P2P, the interaction does not stop when all severs go away
 * just that more people cannot join the session
 * so one should incrementally bring up the clients in that case to demo this facility
 * very cool
 */
public class DemoerOfEarliestResponseDirectReplicatedGroupRPCPort {
	public static void main(String args[]) {
		demo();
	}
	
	public static void demo() {

		
		Class[] classes = {
				AnEarliestResponseReplicatedSessionServer1Launcher.class,
				AnEarliestResponseReplicatedSessionServer2Launcher.class,
				AnEarliestResponseReplicatedSessionServer3Launcher.class,
				AliceEarliestResponseP2PReplicatedPortLauncher.class,
				BobEarliestResponseP2PReplicatedPortLauncher.class,
				CathyEarliestResponseP2PReplicatedPortLauncher.class
				
		};
		MainClassLaunchingUtility.createInteractiveLauncher(classes);
	}
	
	

}
