package replicatedserverport.rpc.simplex;

import port.ParticipantChoice;
import port.sessionserver.SessionParticipantDescription;
import inputport.datacomm.simplex.SimplexClientInputPort;
import inputport.rpc.simplex.SimplexRPCClientInputPort;
import replicatedserverport.datacomm.simplex.object.ReplicatedServerObjectClientPortSelector;

public class AReplicatedServerRPCClientPortFactory implements ReplicatedServerRPCClientPortFactory {	
	
	
	
	@Override
	public SimplexRPCClientInputPort createRPCPort(
			SessionParticipantDescription[] aServerList, String anId, String aName, ParticipantChoice aChoice) {
			SimplexClientInputPort<Object> port = 
					ReplicatedServerObjectClientPortSelector.createReplicatedServerClientInputPort(aServerList, anId, aName, aChoice);
//			ReplicatedServerDuplexObjectClientPortSelector.createDuplexReplicatedServerClientInputPort(aServerList, anId, aName);
			return new AReplicatedServerRPCClientPort(port);
	}

	
}
