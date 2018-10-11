package sessionport.rpc.duplex.direct;

import inputport.ConnectionListener;
import inputport.InputPort;
import inputport.rpc.duplex.ADuplexRPCPortLauncher;
import port.ParticipantChoice;
import port.PortKind;
import port.PortLauncherSupport;
import port.SessionChoice;
import sessionport.rpc.duplex.DuplexRPCSessionPort;
import sessionport.rpc.duplex.relayed.example.ACallingConnectListener;


public class ADuplexRPCDirectSessionPortLauncher extends 
ADuplexRPCPortLauncher {
//AnAbstractPortLauncher{

	public ADuplexRPCDirectSessionPortLauncher (String aSessionServerHost, 
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
//	protected void registerRemoteObjects() {
//		DuplexRPCSessionPort aSessionPort = (DuplexRPCSessionPort) mainPort;
//		Adder adder = new AnAdder();
//		aSessionPort.register(Adder.class, adder);
//		
//	}
	
	// cannot use parameterized version as no launcher support exists for duplex session port in registry right now
//	protected  InputPort getPort() {
//
//		return DuplexRPCSessionPortSelector.createDuplexRPCSessionPort("localhost", 
//				"" + serverId, serverName, getSessionName(), clientId, clientName,
//				getParticipantChoice()
//				);	
//		
//	}

//	protected PortKind getPortKind() {
//		return PortKind.SESSION_PORT;
//	}
//	protected PortAccessKind getPortAccessKind() {
//		return PortAccessKind.DUPLEX;
//	}	
	protected  ConnectionListener getConnectionListener (InputPort anInputPort) {
		return new ACallingConnectListener((DuplexRPCSessionPort) mainPort);
	}

	
	public PortKind getPortKind() {
		return PortKind.SESSION_PORT;
	}
//	public PortAccessKind getPortAccessKind() {
//		return PortAccessKind.DUPLEX;
//	}	
	protected SessionChoice getSessionChoice() {
		return SessionChoice.P2P;
	}
	
}
