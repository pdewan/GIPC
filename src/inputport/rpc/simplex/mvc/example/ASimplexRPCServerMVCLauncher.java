package inputport.rpc.simplex.mvc.example;

import examples.mvc.local.simplex.ASimplexUpperCaser;
import examples.mvc.local.simplex.SimplexUpperCaser;
import inputport.ConnectionListener;
import inputport.InputPort;
import inputport.rpc.simplex.SimplexRPCServerInputPort;
import port.ATracingConnectionListener;
import port.AnAbstractPortLauncher;
import port.ParticipantChoice;
import port.PortAccessKind;
import port.PortKind;
import port.PortLauncherSupport;
import port.SessionChoice;
import port.sessionserver.SessionParticipantDescription;



public class ASimplexRPCServerMVCLauncher extends AnAbstractPortLauncher implements SimplexRPCServerMVCLauncher  {
	public ASimplexRPCServerMVCLauncher(String aServerName,
			String aServerPort) {
		super (aServerName, aServerPort);
	}
	public ASimplexRPCServerMVCLauncher() {
		serverName = MVC_SERVER_NAME;
	}
	public ASimplexRPCServerMVCLauncher(String aSessionServerHost, 
			String aServerId, String aServerName, String aMyId, String aMyName,
			String aSessionName,
			SessionChoice aSessionChoice, 
			boolean aShouldDelay,
			PortLauncherSupport aDelaysSupport,
			boolean aDoJitter,
			boolean aDoCausal, ParticipantChoice aChoice) {
		super(aSessionServerHost, aServerId, aServerName, aMyId, aMyName, aSessionName, aSessionChoice, aShouldDelay, aDelaysSupport, aDoJitter, aDoCausal, aChoice);

	}
	public ASimplexRPCServerMVCLauncher(SessionParticipantDescription[] aServerList, String aMyId, String aMyName,

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
	public PortKind getPortKind() {
		return PortKind.SERVER_INPUT_PORT;
	}
	@Override
	public PortAccessKind getPortAccessKind() {
		return PortAccessKind.SIMPLEX;
	}
//	protected PortLauncherSupport getPortLauncherSupport() {
//		return new ASimplexRPCInputPortLauncherSupport();
//	}
	protected  ConnectionListener getConnectionListener (InputPort anInputPort) {
		return new ATracingConnectionListener(anInputPort);
	}
//	protected InputPort getPort() {
//		return SimplexRPCInputPortSelector.createSimplexRPCServerInputPort(
//				serverId, 
//				serverName);
//	}
	protected void registerRemoteObjects() {
		SimplexRPCServerInputPort aSimplexRPCServerInputPort = (SimplexRPCServerInputPort) mainPort;
		SimplexUpperCaser upperCaser = getUpperCaser();
		aSimplexRPCServerInputPort.register(upperCaser);
	}
	protected SimplexUpperCaser getUpperCaser() {
		return new ASimplexUpperCaser();
	}
	public static void main (String[] args) {
		(new ASimplexRPCServerMVCLauncher(MVC_SERVER_NAME, MVC_SERVER_ID)).launch();
	}
	



	
	
}
