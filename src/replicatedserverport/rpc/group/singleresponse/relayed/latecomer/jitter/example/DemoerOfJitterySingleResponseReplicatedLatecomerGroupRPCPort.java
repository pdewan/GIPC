/* the number of servers can vary from 1 to 3 but they
 * all have to be started before interaction. All of them compute
 * the RPC calls, but only one sends a result. 
 * however, there is fault tolerance, as long as there is a server the
 * dialogue goes on. The server failure is communicated to clients as exeptions 
 * since this is latecomer, the interaction   stops when all severs go away
 * Only alice and cathy can input, bob is a server
 * supposed to be jittery
 * 
 */
package replicatedserverport.rpc.group.singleresponse.relayed.latecomer.jitter.example;

import bus.uigen.pipe.MainClassLaunchingUtility;
import util.trace.ImplicitKeywordKind;
import util.trace.Tracer;
import util.trace.console.ConsoleInput;
import util.trace.console.ConsoleOutput;
import util.trace.uigen.ClassAdapterReceivedPropertyChangeEvent;

public class DemoerOfJitterySingleResponseReplicatedLatecomerGroupRPCPort {
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
				AJitterySingleResponseReplicatedLatecomerSessionServer1Launcher.class,
				AJitterySingleResponseReplicatedLatecomerSessionServer2Launcher.class,
				AJitterySingleResponseReplicatedLatecomerSessionServer3Launcher.class,
				AliceJitterySingleResponseLatecomerReplicatedPortLauncher.class,
				BobJitterySingleResponseLatecomerReplicatedPortLauncher.class,
				CathyJitterySingleResponseLatecomerReplicatedPortLauncher.class
				
		};
		MainClassLaunchingUtility.createInteractiveLauncher(classes);
	}
	
	

}
