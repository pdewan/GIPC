package inputport.rpc.group.echoer.example;

import inputport.rpc.duplex.DuplexRPCServerInputPort;
import inputport.rpc.duplex.echoer.example.AnEchoingDuplexRPCServerLauncher;
import inputport.rpc.group.GroupRPCProxyGenerator;
import inputport.rpc.group.GroupRPCServerInputPort;
import inputport.rpc.group.mvc.collaborative.example.ACollaborativeUpperCaser;
import port.PortAccessKind;
import examples.mvc.local.duplex.Counter;
import examples.mvc.local.duplex.DuplexUpperCaser;



public class AnEchoingGroupRPCServerCollaborativeLauncher extends AnEchoingDuplexRPCServerLauncher/* AMultiUserDuplexRPCServerMVCLauncher*/   {
	public AnEchoingGroupRPCServerCollaborativeLauncher(String aServerName,
			String aServerPort) {
		super (aServerName, aServerPort);
	}
	
//	protected PortLauncherSupport getPortLauncherSupport() {
//		return new AGroupRPCInputPortLauncherSupport();
//	}
	
	@Override
	public PortAccessKind getPortAccessKind() {
		return PortAccessKind.GROUP;
	}
//	protected InputPort getPort() {
//		return GroupRPCInputPortSelector.createGroupRPCServerInputPort(
//				serverId, 
//				serverName);
//	}	
	protected  void createProxies() {
		counter = (Counter) GroupRPCProxyGenerator.generateAllRPCProxy((GroupRPCServerInputPort) mainPort, registeredCounterClass());
	}
	protected DuplexUpperCaser getUpperCaser() {
		return new ACollaborativeUpperCaser(counter, (DuplexRPCServerInputPort) mainPort);
	}
	
	public static void main (String[] args) {
		(new AnEchoingGroupRPCServerCollaborativeLauncher(ECHOER_SERVER_NAME, ECHOER_SERVER_ID)).launch();
	}	
}
