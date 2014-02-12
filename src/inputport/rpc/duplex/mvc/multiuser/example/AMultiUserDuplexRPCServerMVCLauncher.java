package inputport.rpc.duplex.mvc.multiuser.example;

import port.ParticipantChoice;
import port.PortLauncherSupport;
import port.SessionChoice;
import port.sessionserver.SessionParticipantDescription;
import inputport.rpc.duplex.DuplexRPCInputPort;
import inputport.rpc.duplex.DuplexRPCServerInputPort;
import inputport.rpc.duplex.ReplyRPCProxyGenerator;
import inputport.rpc.duplex.mvc.singleuser.example.ADuplexRPCServerMVCLauncher;
import examples.mvc.local.duplex.Counter;
import examples.mvc.local.simplex.SimplexUpperCaser;



public class AMultiUserDuplexRPCServerMVCLauncher extends ADuplexRPCServerMVCLauncher   {
	public AMultiUserDuplexRPCServerMVCLauncher(String aServerName,
			String aServerPort) {
		super (aServerName, aServerPort);
	}
	public AMultiUserDuplexRPCServerMVCLauncher(String aSessionServerHost, 
			String aServerId, String aServerName, String aMyId, String aMyName,
			String aSessionName,
			SessionChoice aSessionChoice, 
			boolean aShouldDelay,
			PortLauncherSupport aDelaysSupport,
			boolean aDoJitter,
			boolean aDoCausal, ParticipantChoice aChoice) {
		super(aSessionServerHost, aServerId, aServerName, aMyId, aMyName, aSessionName, aSessionChoice, aShouldDelay, aDelaysSupport, aDoJitter, aDoCausal, aChoice);

	}
	public AMultiUserDuplexRPCServerMVCLauncher(SessionParticipantDescription[] aServerList, String aMyId, String aMyName,

			String aSessionName,
			SessionChoice aSessionChoice, 
			boolean aShouldDelay,
			PortLauncherSupport aDelaysSupport,
			boolean aDoJitter,
			boolean aDoCausal, 			
			ParticipantChoice aChoice) {
		super(aServerList, aMyId, aMyName, aSessionName, aSessionChoice, aShouldDelay, aDelaysSupport, aDoJitter, aDoCausal, aChoice);
	}
	public AMultiUserDuplexRPCServerMVCLauncher() {
	}
	protected  void createProxies() {
    	counter = (Counter) ReplyRPCProxyGenerator.generateReplyRPCProxy((DuplexRPCServerInputPort) mainPort, (registeredCounterClass()));
	}
	public static void main (String[] args) {
		(new AMultiUserDuplexRPCServerMVCLauncher(MVC_SERVER_NAME, MVC_SERVER_ID)).launch();
	}	
}
