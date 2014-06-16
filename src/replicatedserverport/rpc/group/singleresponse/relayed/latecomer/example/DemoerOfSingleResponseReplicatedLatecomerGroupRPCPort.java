/* the number of servers can vary from 1 to 3 but they
 * all have to be started before interaction. All of them compute
 * the RPC calls, but only one sends a result. 
 * however, there is fault tolerance, as long as there is a server the
 * dialogue goes on. The server failure is communicated to clients as exeptions 
 * since this is latecomer, the interaction   stops when all severs go away
 * Only alice and cathy can input, bob is a server
 * 
 */
package replicatedserverport.rpc.group.singleresponse.relayed.latecomer.example;

import bus.uigen.models.MainClassLaunchingUtility;
import util.trace.Tracer;

public class DemoerOfSingleResponseReplicatedLatecomerGroupRPCPort {
	public static void main(String args[]) {
		
		demo();
	}
	
	public static void demo() {

		
		Class[] classes = {
				ASingleResponseReplicatedLatecomerSessionServer1Launcher.class,
				ASingleResponseReplicatedLatecomerSessionServer2Launcher.class,
				ASingleResponseReplicatedLatecomerSessionServer3Launcher.class,
				AliceSingleResponseLatecomerReplicatedPortLauncher.class,
				BobSingleResponseLatecomerReplicatedPortLauncher.class,
				ACathySingleResponseLatecomerReplicatedPortLauncher.class
				
		};
		MainClassLaunchingUtility.createInteractiveLauncher(classes);
	}
	
	

}
