package replicatedserverport.rpc.duplex;

import port.ParticipantChoice;
import port.sessionserver.SessionParticipantDescription;
import inputport.datacomm.duplex.DuplexClientInputPort;
import inputport.rpc.duplex.ADuplexRPCInputPortFactory;
import inputport.rpc.duplex.DuplexRPCClientInputPort;
import replicatedserverport.datacomm.duplex.object.ReplicatedServerDuplexObjectClientPortSelector;

public class AReplicatedServerDuplexRPCClientPortFactory extends ADuplexRPCInputPortFactory implements ReplicatedServerDuplexRPCClientPortFactory {	
	
	public AReplicatedServerDuplexRPCClientPortFactory() {
		super();
	}
	
	@Override
	public DuplexRPCClientInputPort createDuplexRPCPort(
			SessionParticipantDescription[] aServerList, String aLogicalSeverName, String aName,
			ParticipantChoice aChoice) {
			DuplexClientInputPort<Object> duplexPort = 
					ReplicatedServerDuplexObjectClientPortSelector.createDuplexReplicatedServerClientInputPort(aServerList, aLogicalSeverName, aName, aChoice);
//			ReplicatedServerDuplexObjectClientPortSelector.createDuplexReplicatedServerClientInputPort(aServerList, anId, aName);
			return new AReplicatedServerDuplexRPCClientPort(duplexPort);
	}
}
