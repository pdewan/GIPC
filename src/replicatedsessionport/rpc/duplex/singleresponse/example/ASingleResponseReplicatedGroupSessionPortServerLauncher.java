package replicatedsessionport.rpc.duplex.singleresponse.example;

import inputport.InputPort;
import inputport.rpc.duplex.example.DuplexCounterAndSenderAwareSummer;
import inputport.rpc.group.GroupRPCServerInputPort;
import inputport.rpc.group.adder.example.AGroupCounterAndSenderAwareSumPrinter;
import port.PortLauncherSupport;
import port.SessionChoice;
import replicatedsessionport.rpc.duplex.ReplicatedSessionDuplexRPCPortSelector;

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
