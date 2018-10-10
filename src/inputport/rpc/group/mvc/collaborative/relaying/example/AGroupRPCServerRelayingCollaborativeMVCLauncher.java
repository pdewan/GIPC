package inputport.rpc.group.mvc.collaborative.relaying.example;

import bus.uigen.ObjectEditor;
import examples.mvc.local.simplex.SimplexUpperCaser;
import inputport.rpc.duplex.DuplexRPCServerInputPort;
import inputport.rpc.group.mvc.collaborative.example.AGroupRPCServerCollaborativeMVCLauncher;
import util.trace.Tracer;
import util.trace.uigen.TraceableDisplayAndWaitManagerFactory;



public class AGroupRPCServerRelayingCollaborativeMVCLauncher extends AGroupRPCServerCollaborativeMVCLauncher   {
	public AGroupRPCServerRelayingCollaborativeMVCLauncher(String aServerName,
			String aServerPort) {
		super (aServerName, aServerPort);
		ObjectEditor.edit(TraceableDisplayAndWaitManagerFactory.getTraceableDisplayAndPrintManager());
		Tracer.setKeywordDisplayStatus(this, true);
	}
	public AGroupRPCServerRelayingCollaborativeMVCLauncher() {
	}	
	protected SimplexUpperCaser getUpperCaser() {
		return new ARelayingCollaborativeUpperCaser(counter, (DuplexRPCServerInputPort) mainPort);
	}
	public static void main (String[] args) {
//		OEFrame frame = ObjectEditor.edit(TraceableDisplayAndWaitManagerFactory.getTraceableDisplayAndPrintManager());
//		Tracer.setKeywordDisplayStatus(AGroupRPCServerRelayingCollaborativeMVCLauncher.class, true);
		(new AGroupRPCServerRelayingCollaborativeMVCLauncher(MVC_SERVER_NAME, MVC_SERVER_ID)).launch();
	}	
}
