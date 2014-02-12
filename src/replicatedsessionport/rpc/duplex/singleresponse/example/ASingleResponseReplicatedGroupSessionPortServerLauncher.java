package replicatedsessionport.rpc.duplex.singleresponse.example;

import inputport.InputPort;
import inputport.rpc.RPCRegistry;
import inputport.rpc.duplex.example.AnotherCounter;
import inputport.rpc.duplex.example.DuplexCounterAndSenderAwareSummer;
import inputport.rpc.group.GroupRPCServerInputPort;
import inputport.rpc.group.adder.example.ACounterWithObjectValue;
import inputport.rpc.group.adder.example.AGroupCounterAndSenderAwareSumPrinter;
import port.ParticipantChoice;
import port.PortLauncherSupport;
import port.SessionChoice;
import port.jitter.AJitterControllingPortLauncherSupport;
import replicatedsessionport.datacomm.duplex.object.ReplicatedSessionDuplexObjectPortSelector;
import replicatedsessionport.rpc.duplex.ReplicatedSessionDuplexRPCPortSelector;
import sessionport.datacomm.group.object.ObjectGroupSessionPortSelector;
import sessionport.datacomm.group.object.direct.AnObjectGroupSessionPortDirectLauncherSupport;
import sessionport.datacomm.group.object.flexible.jitter.example.AJitteryFlexibleSessionPortClientLauncher;
import sessionport.datacomm.group.object.relayed.AnObjectGroupSessionPortRelayedLauncherSupport;
import sessionport.datacomm.group.object.relayed.latecomer.AnObjectGroupSessionPortLatecomerLauncherSupport;

public class ASingleResponseReplicatedGroupSessionPortServerLauncher 
	extends AJitteryFlexibleReplicatedGroupSessionPortServerLauncher{

	public ASingleResponseReplicatedGroupSessionPortServerLauncher(String aSessionServerHost, 
			String aServerId, String aServerName, String aMyId, String aMyName, 
			SessionChoice aSessionChoice, 
			boolean aShouldDelay,
			PortLauncherSupport aDelaysSupport,
			boolean aDoCausal,
			boolean aDoJitter) {
		super(aSessionServerHost, aServerId, aServerName, aMyId, aMyName, aSessionChoice, aShouldDelay, aDelaysSupport, aDoCausal, aDoJitter);
	}

	
	protected  InputPort getPort() {		
		return 
				ReplicatedSessionDuplexRPCPortSelector.
				   createReplicatedSessionGroupRPCServerInputPort(serverHost, serverId, serverName, SESSION_NAME, myId, myName, null);
		
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
	

}
