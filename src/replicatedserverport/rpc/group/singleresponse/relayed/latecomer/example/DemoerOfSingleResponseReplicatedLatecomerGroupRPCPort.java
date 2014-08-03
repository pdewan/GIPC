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

import bus.uigen.pipes.MainClassLaunchingUtility;
import bus.uigen.trace.ClassAdapterReceivedPropertyChangeEvent;
import util.trace.ImplicitKeywordKind;
import util.trace.Tracer;
import util.trace.console.ConsoleInput;
import util.trace.console.ConsoleOutput;

public class DemoerOfSingleResponseReplicatedLatecomerGroupRPCPort {
	public static void main(String args[]) {
		Tracer.showInfo(true);
		Tracer.setImplicitPrintKeywordKind(ImplicitKeywordKind.OBJECT_CLASS_NAME);
		Tracer.setKeywordPrintStatus(ConsoleInput.class, true);
		Tracer.setKeywordPrintStatus(ConsoleOutput.class, true);
		Tracer.setKeywordPrintStatus(ClassAdapterReceivedPropertyChangeEvent.class, true);

		demo();
	}
	
	public static void demo() {

		
		Class[] classes = {
				ASingleResponseReplicatedLatecomerSessionServer1Launcher.class,
				ASingleResponseReplicatedLatecomerSessionServer2Launcher.class,
				ASingleResponseReplicatedLatecomerSessionServer3Launcher.class,
				AliceSingleResponseLatecomerReplicatedPortLauncher.class,
				BobSingleResponseLatecomerReplicatedPortLauncher.class,
				CathySingleResponseLatecomerReplicatedPortLauncher.class
				
		};
		MainClassLaunchingUtility.createInteractiveLauncher(classes);
	}
	
	

}
