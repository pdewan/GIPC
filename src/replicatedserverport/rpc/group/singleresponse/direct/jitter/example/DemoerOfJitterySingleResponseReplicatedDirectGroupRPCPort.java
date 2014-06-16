/* the number of servers can vary from 1 to 3 but they
 * all have to be started before interaction. All of them compute
 * the RPC calls, but only one sends a result. 
 * however, there is fault tolerance, as long as there is a server the
 * dialogue goes on. The server failure is communicated to clients as exeptions 
 * since this is P2P, the interaction does not stop when all severs go away
 * just that more people cannot join the session
 * so one should incrementally bring up the clients in that case to demo this facility
 * very cool
 * 
 * did notice any delays or jitter in the console I/O
 */
package replicatedserverport.rpc.group.singleresponse.direct.jitter.example;

import bus.uigen.models.MainClassLaunchingUtility;

public class DemoerOfJitterySingleResponseReplicatedDirectGroupRPCPort {
	public static void main(String args[]) {
		demo();
	}
	
	public static void demo() {

		
		Class[] classes = {
				AJitterySingleResponseReplicatedSessionServer1Launcher.class,
				AJitterySingleResponseReplicatedSessionServer2Launcher.class,
				AJitterySingleResponseReplicatedSessionServer3Launcher.class,
				AliceJitterySingleResponseP2PReplicatedPortLauncher.class,
				BobJitterySingleResponseP2PReplicatedPortLauncher.class,
				CathyJitterySingleResponseP2PReplicatedPortLauncher.class
				
		};
		MainClassLaunchingUtility.createInteractiveLauncher(classes);
	}
	
	

}
