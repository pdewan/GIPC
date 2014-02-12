package inputport.rpc.group.mvc.collaborative.relaying.example;

import port.PortLauncherSupport;
import util.trace.Tracer;
import bus.uigen.OEFrame;
import bus.uigen.ObjectEditor;
import bus.uigen.trace.TraceableDisplayAndWaitManagerFactory;
import inputport.InputPort;
import inputport.rpc.duplex.DuplexRPCServerInputPort;
import inputport.rpc.group.AGroupRPCInputPortLauncherSupport;
import inputport.rpc.group.GroupRPCInputPortSelector;
import inputport.rpc.group.GroupRPCProxyGenerator;
import inputport.rpc.group.GroupRPCServerInputPort;
import inputport.rpc.group.mvc.collaborative.example.ACollaborativeUpperCaser;
import inputport.rpc.group.mvc.collaborative.example.AGroupRPCServerCollaborativeMVCLauncher;
import examples.mvc.local.duplex.Counter;
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
