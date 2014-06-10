package sessionport.rpc.duplex.example;

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
import port.old.Adder;
import port.old.AnAdder;
import replicatedserverport.rpc.group.ReplicatedServerSessionPortSelector;
import replicatedserverport.rpc.group.flexibleresponse.flexible.example.AnEarliestReponseReplicatedSessionServerLauncher;
import sessionport.datacomm.group.object.flexible.AFlexibleSessionPortClientLauncher;
import sessionport.rpc.duplex.DuplexRPCSessionPort;
import sessionport.rpc.duplex.DuplexRPCSessionPortSelector;


public class AModularDuplexRPCSessionPortLauncher extends AnAbstractPortLauncher{
//	public CopyOfADuplexRPCSessionPortLauncher(String aSessionServerHost,
//			String aServerId, String aServerName, String aMyId, String aMyName,
//			SessionChoice aSessionChoice, boolean aShouldDelay,
//			PortLauncherSupport aDelaysSupport, boolean aDoCausal,
//			ParticipantChoice aChoice) {
//		super(aSessionServerHost, aServerId, aServerName, aMyId, aMyName,
//				aSessionChoice, aShouldDelay, aDelaysSupport, aDoCausal, aChoice);
//	}
	public AModularDuplexRPCSessionPortLauncher (String aSessionServerHost, 
			String aServerId, 
			String aServerName, 
			String aMyId, 
			String aMyName, 
			String aSessionName,
			SessionChoice aSessionChoice, // not used
			boolean aShouldDelay,
			PortLauncherSupport aDelaysSupport,
			boolean aDoJitter,
			boolean aDoCausal, 			
			ParticipantChoice aChoice) {
		super(aSessionServerHost, aServerId, aServerName, aMyId, aMyName, aSessionName, aSessionChoice, aShouldDelay, aDelaysSupport, aDoJitter, aDoCausal, aChoice);
		
	}
	protected void registerRemoteObjects() {
		DuplexRPCSessionPort aSessionPort = (DuplexRPCSessionPort) mainPort;
		Adder adder = new AnAdder();
		aSessionPort.register(Adder.class, adder);
		
	}
	
	// cannot use parameterized version as no launcher support exists for duplex session port in registry right now
	protected  InputPort getPort() {

		return DuplexRPCSessionPortSelector.createDuplexRPCSessionPort("localhost", 
				"" + serverId, serverName, sessionName, clientId, clientName,
				participantChoice
				);	
		
	}

//	protected PortKind getPortKind() {
//		return PortKind.SESSION_PORT;
//	}
//	protected PortAccessKind getPortAccessKind() {
//		return PortAccessKind.DUPLEX;
//	}	
	protected  ConnectionListener getConnectionListener (InputPort anInputPort) {
		return new ACallingConnectListener((DuplexRPCSessionPort) mainPort);
	}
//
//	static final int SESSION_SERVER_PORT = 9090;
//	static final String SESSION_SERVER_NAME = "SESSION SERVER";
//
//	public static void launchSessionPartipant( String anId, String aName, ParticipantChoice aChoice) {
////		Tracer.showInfo(true);
//		DuplexRPCSessionPort sessionPort =CopyOfDuplexRPCSessionPortSelector.createDuplexRPCSessionPort("localhost", 
//				"" + SESSION_SERVER_PORT, SESSION_SERVER_NAME, "Test Session", anId, aName,
//				aChoice
//				);	
//		ConnectionListener connectListener = new ACallingConnectListener(sessionPort);
//		sessionPort.addConnectionListener(connectListener);
//		Adder adder = new AnAdder();
//		sessionPort.register(Adder.class, adder);
//		sessionPort.connect();		
//	}
	

}
