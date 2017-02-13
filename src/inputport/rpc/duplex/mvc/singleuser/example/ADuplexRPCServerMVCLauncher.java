package inputport.rpc.duplex.mvc.singleuser.example;

import inputport.rpc.duplex.DuplexRPCServerInputPort;
import inputport.rpc.simplex.mvc.example.ASimplexRPCServerMVCLauncher;
import port.ParticipantChoice;
import port.PortAccessKind;
import port.PortLauncherSupport;
import port.SessionChoice;
import port.sessionserver.SessionParticipantDescription;
import examples.mvc.local.duplex.ADuplexUpperCaser;
import examples.mvc.local.duplex.Counter;
import examples.mvc.local.simplex.SimplexUpperCaser;
public class ADuplexRPCServerMVCLauncher extends ASimplexRPCServerMVCLauncher  implements DuplexRPCServerMVCLauncher {
	protected Counter counter;
	public ADuplexRPCServerMVCLauncher(String aServerName,
			String aServerPort) {
		super (aServerName, aServerPort);
	}
	public ADuplexRPCServerMVCLauncher(String aSessionServerHost, 
			String aServerId, String aServerName, String aMyId, String aMyName,
			String aSessionName,
			SessionChoice aSessionChoice, 
			boolean aShouldDelay,
			PortLauncherSupport aDelaysSupport,
			boolean aDoJitter,
			boolean aDoCausal, ParticipantChoice aChoice) {
		super(aSessionServerHost, aServerId, aServerName, aMyId, aMyName, aSessionName, aSessionChoice, aShouldDelay, aDelaysSupport, aDoJitter, aDoCausal, aChoice);

	}
	public ADuplexRPCServerMVCLauncher(SessionParticipantDescription[] aServerList, String aMyId, String aMyName,

			String aSessionName,
			SessionChoice aSessionChoice, 
			boolean aShouldDelay,
			PortLauncherSupport aDelaysSupport,
			boolean aDoJitter,
			boolean aDoCausal, 			
			ParticipantChoice aChoice) {
		super(aServerList, aMyId, aMyName, aSessionName, aSessionChoice, aShouldDelay, aDelaysSupport, aDoJitter, aDoCausal, aChoice);
	}
	public ADuplexRPCServerMVCLauncher() {
	}
	@Override
	public PortAccessKind getPortAccessKind() {
		return PortAccessKind.DUPLEX;
	}
//	protected PortLauncherSupport getPortLauncherSupport() {
//		return new ADuplexRPCInputPortLauncherSupport();
//	}	

//	protected InputPort getPort() {
//		return DuplexRPCInputPortSelector.createDuplexRPCServerInputPort(
//				serverId, 
//				serverName);
//	}		
	protected  void createProxies() {
		// both will work, better to use create communicatable proxies
		counter = (Counter) ((DuplexRPCServerInputPort) mainPort).getRPCProxyGenerator().generateRPCProxy(ADuplexRPCClientMVCLauncher.CLIENT_NAME, 
    			registeredCounterClass());
//    	counter = (Counter) DirectedRPCProxyGenerator.generateRPCProxy((DuplexRPCServerInputPort) mainPort, ADuplexRPCClientMVCLauncher.CLIENT_NAME, 
//    			registeredCounterClass());
	}
	protected Class registeredCounterClass() {
		return DuplexRPCClientMVCLauncher.REGISTERED_COUNTER_CLASS;
	}	
	protected SimplexUpperCaser getUpperCaser() {
		return new ADuplexUpperCaser(counter);
	}
	public static void main (String[] args) {
		(new ADuplexRPCServerMVCLauncher(MVC_SERVER_NAME, MVC_SERVER_ID)).launch();
	}	
}
