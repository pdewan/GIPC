package sessionport.rpc.group;

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
import replicatedserverport.rpc.group.ReplicatedServerSessionPortSelector;
import replicatedserverport.rpc.group.flexibleresponse.flexible.AnEarliestReponseReplicatedSessionServerLauncher;
import sessionport.datacomm.group.object.flexible.AFlexibleSessionPortClientLauncher;
import sessionport.rpc.duplex.DuplexRPCSessionPort;
import sessionport.rpc.duplex.DuplexRPCSessionPortSelector;
import sessionport.rpc.duplex.direct.example.AModularDuplexRPCDirectSessionPortLauncher;
import sessionport.rpc.duplex.relayed.example.ACallingConnectListener;
import sessionport.rpc.duplex.relayed.example.Adder;
import sessionport.rpc.duplex.relayed.example.AnAdder;
import sessionport.rpc.group.direct.example.AGroupCallingConnectListener;


public class AGroupRPCDirectSessionPortLauncher extends
	AModularDuplexRPCDirectSessionPortLauncher
//	extends AnAbstractPortLauncher
	{

	public AGroupRPCDirectSessionPortLauncher (String aSessionServerHost, 
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

	protected  ConnectionListener getConnectionListener (InputPort anInputPort) {
		return new AGroupCallingConnectListener((GroupRPCSessionPort) mainPort);
	}

	public PortAccessKind getPortAccessKind() {
		return PortAccessKind.GROUP;
	}	
	protected SessionChoice getSessionChoice() {
		return SessionChoice.P2P;
	}
	
}
