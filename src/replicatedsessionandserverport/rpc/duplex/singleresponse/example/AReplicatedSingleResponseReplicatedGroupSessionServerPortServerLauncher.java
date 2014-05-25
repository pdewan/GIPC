package replicatedsessionandserverport.rpc.duplex.singleresponse.example;

import inputport.InputPort;
import inputport.rpc.duplex.example.DuplexCounterAndSenderAwareSummer;
import inputport.rpc.group.GroupRPCServerInputPort;
import inputport.rpc.group.adder.example.AGroupCounterAndSenderAwareSumPrinter;
import port.PortLauncherSupport;
import port.SessionChoice;
import port.sessionserver.SessionParticipantDescription;
import replicatedserverport.rpc.group.flexibleresponse.flexible.example.ReplicationChoice;
import replicatedsessionport.rpc.duplex.ReplicatedSessionDuplexRPCPortSelector;

public class AReplicatedSingleResponseReplicatedGroupSessionServerPortServerLauncher 
	extends AJitteryFlexibleReplicatedGroupSessionServerPortServerLauncher{

	public AReplicatedSingleResponseReplicatedGroupSessionServerPortServerLauncher(
			String aSessionServerHost, 
			String aServerId, String aServerName, String aMyId, String aMyName, 
			SessionChoice aSessionChoice, 
			boolean aShouldDelay,
			PortLauncherSupport aDelaysSupport,
			boolean aDoCausal,
//			ReplicationChoice aReplicationChoice,
			SessionParticipantDescription[] aServersDescription,
			boolean aDoJitter) {
		super(aSessionServerHost, aServerId, aServerName, aMyId, aMyName, aSessionChoice, aShouldDelay, aDelaysSupport, aDoCausal, ReplicationChoice.SINGLE_RESPONSE, aServersDescription, aDoJitter);
	}

	
	protected  InputPort getPort() {		
		return 
				ReplicatedSessionDuplexRPCPortSelector.
				   createReplicatedSessionGroupRPCServerInputPort(serverHost, serverId, serverName, SESSION_NAME, myId, myName, serversDescription);
		
	}
//	protected Counter createAndRegisterCounter(RPCRegistry aClientInputPort) {
//		Counter counter = new ACounterWithObjectValue();
//		aClientInputPort.register(counter);
//		return counter;
//	}	
	@Override
	protected void registerRemoteObjects()  {
		GroupRPCServerInputPort aGroupServerInputPort = (GroupRPCServerInputPort) mainPort;
		DuplexCounterAndSenderAwareSummer adder = new AGroupCounterAndSenderAwareSumPrinter(aGroupServerInputPort);
		aGroupServerInputPort.register(adder);
	}	
	@Override
	protected void doPostConnectsAsyncOperations() {
		System.out.println("Ready to serve  clients.");		
	}
	

}
