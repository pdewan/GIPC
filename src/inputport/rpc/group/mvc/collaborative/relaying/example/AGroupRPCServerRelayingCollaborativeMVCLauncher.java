package inputport.rpc.group.mvc.collaborative.relaying.example;

import inputport.rpc.duplex.DuplexRPCServerInputPort;
import inputport.rpc.group.mvc.collaborative.example.AGroupRPCServerCollaborativeMVCLauncher;
import util.trace.Tracer;
import bus.uigen.ObjectEditor;
import bus.uigen.trace.TraceableDisplayAndWaitManagerFactory;
import examples.mvc.local.simplex.SimplexUpperCaser;



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
