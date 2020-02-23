package inputport.rpc.group.mvc.collaborative.example;

import examples.counter.Counter;
import examples.mvc.local.simplex.SimplexUpperCaser;
import inputport.rpc.duplex.DuplexRPCServerInputPort;
import inputport.rpc.duplex.mvc.multiuser.example.AMultiUserDuplexRPCServerMVCLauncher;
import inputport.rpc.group.GroupRPCProxyGenerator;
import inputport.rpc.group.GroupRPCServerInputPort;
import port.ParticipantChoice;
import port.PortAccessKind;
import port.PortLauncherSupport;
import port.SessionChoice;
import port.sessionserver.SessionParticipantDescription;



public class AGroupRPCServerCollaborativeMVCLauncher extends AMultiUserDuplexRPCServerMVCLauncher   {
	public AGroupRPCServerCollaborativeMVCLauncher(String aServerName,
			String aServerPort) {
		super (aServerName, aServerPort);
	}
	public AGroupRPCServerCollaborativeMVCLauncher() {
	}
//	protected PortLauncherSupport getPortLauncherSupport() {
//		return new AGroupRPCInputPortLauncherSupport();
//	}
	public AGroupRPCServerCollaborativeMVCLauncher(String aSessionServerHost, 
			String aSessionServerId, String aSessionServerName, String aMyId, String aMyName,
			String aSessionName,
			SessionChoice aSessionChoice, 
			boolean aShouldDelay,
			PortLauncherSupport aDelaysSupport,
			boolean aDoJitter,
			boolean aDoCausal, ParticipantChoice aChoice) {
		super(aSessionServerHost, aSessionServerId, aSessionServerName, aMyId, aMyName, aSessionName, aSessionChoice, aShouldDelay, aDelaysSupport, aDoJitter, aDoCausal, aChoice);

	}
	public AGroupRPCServerCollaborativeMVCLauncher(SessionParticipantDescription[] aServerList, String aMyId, String aMyName,

			String aSessionName,
			SessionChoice aSessionChoice, 
			boolean aShouldDelay,
			PortLauncherSupport aDelaysSupport,
			boolean aDoJitter,
			boolean aDoCausal, 			
			ParticipantChoice aChoice) {
		super(aServerList, aMyId, aMyName, aSessionName, aSessionChoice, aShouldDelay, aDelaysSupport, aDoJitter, aDoCausal, aChoice);
	}
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
	protected SimplexUpperCaser getUpperCaser() {
		return new ACollaborativeUpperCaser(counter, (DuplexRPCServerInputPort) mainPort);
	}
	public static void main (String[] args) {
		(new AGroupRPCServerCollaborativeMVCLauncher(MVC_SERVER_NAME, MVC_SERVER_ID)).launch();
	}	
}
