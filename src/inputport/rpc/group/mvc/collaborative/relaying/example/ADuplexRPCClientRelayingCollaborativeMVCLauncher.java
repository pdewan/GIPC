package inputport.rpc.group.mvc.collaborative.relaying.example;


import inputport.rpc.duplex.mvc.singleuser.example.ADuplexRPCClientMVCLauncher;
import util.trace.Tracer;
import util.trace.uigen.TraceableDisplayAndWaitManagerFactory;
import bus.uigen.OEFrame;
import bus.uigen.ObjectEditor;
import examples.mvc.local.duplex.DuplexFrostyModel;
import examples.mvc.rmi.collaborative.relaying.AnEchoer;
import examples.mvc.rmi.collaborative.relaying.Echoer;

public class ADuplexRPCClientRelayingCollaborativeMVCLauncher extends ADuplexRPCClientMVCLauncher  {
	Echoer echoer;	
	public ADuplexRPCClientRelayingCollaborativeMVCLauncher(String aClientName) {
		super(aClientName);
	}
	public ADuplexRPCClientRelayingCollaborativeMVCLauncher() {
	}
	public ADuplexRPCClientRelayingCollaborativeMVCLauncher(String aClientName, String aServerHost, String aServerId, String aServerName) {
		super(aClientName, aServerHost, aServerId, aServerName);	
		ObjectEditor.edit(TraceableDisplayAndWaitManagerFactory.getTraceableDisplayAndPrintManager());
		Tracer.setKeywordDisplayStatus(this, true);
	}
	protected Class registeredUpperCaserClass() {
		return GroupRPCServerRelayingCollaborativeMVCLauncher.REGISTERED_RELAYING_UPPER_CASER_CLASS;
	}
//	@Override
//	protected  void createProxies() {
//
//    	upperCaseProxy = (RelayingCollaborativeUpperCaser) DirectedRPCProxyGenerator.generateRPCProxy((DuplexRPCClientInputPort) mainPort, 
//				registeredUpperCaserClass());
//
//	}	
	protected DuplexFrostyModel getFrostyModel() {
		return new  ARelayingCollaborativeFrostyModel((RelayingCollaborativeUpperCaser)upperCaseProxy, new AnEchoer(), counter, clientName);	
	}	
	public static void main (String[] args) {
		Tracer.setKeywordWaitStatus(AGroupRPCServerRelayingCollaborativeMVCLauncher.class, true);
		OEFrame frame = ObjectEditor.edit(TraceableDisplayAndWaitManagerFactory.getTraceableDisplayAndPrintManager());
		(new ADuplexRPCClientRelayingCollaborativeMVCLauncher()).launch();
	}
}
