/* the number of servers can vary from 1 to 3 but they
 * all have to be started before interaction. All of them compute
 * the RPC calls, but only one sends a result. 
 * however, there is fault tolerance, as long as there is a server the
 * dialogue goes on. The server failure is communicated to clients as exeptions 
 * since this is P2P, the interaction does not stop when all severs go away
 * just that more people cannot join the session
 * so one should incrementally bring up the clients in that case to demo this facility
 * very cool
 */
package replicatedserverport.rpc.group.singleresponse.direct.example;

import bus.uigen.models.MainClassLaunchingUtility;

public class DemoerOfSingleResponseReplicatedDirectGroupRPCPort {
	public static void main(String args[]) {
		demo();
	}
	
	public static void demo() {

		
		Class[] classes = {
				ASingleResponseReplicatedSessionServer1Launcher.class,
				ASingleResponseReplicatedSessionServer2Launcher.class,
				ASingleResponseReplicatedSessionServer3Launcher.class,
				AliceSingleResponseP2PReplicatedPortLauncher.class,
				BobSingleResponseP2PReplicatedPortLauncher.class,
				CathySingleResponseP2PReplicatedPortLauncher.class
				
		};
		MainClassLaunchingUtility.createInteractiveLauncher(classes);
	}
	
	

}
