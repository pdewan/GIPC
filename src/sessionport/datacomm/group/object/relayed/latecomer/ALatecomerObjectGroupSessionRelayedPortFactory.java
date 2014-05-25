package sessionport.datacomm.group.object.relayed.latecomer;


import inputport.rpc.duplex.DuplexRPCClientInputPort;
import inputport.rpc.duplex.DuplexRPCInputPortSelector;
import port.ParticipantChoice;
import sessionport.datacomm.group.GroupSessionPort;
import sessionport.datacomm.group.GroupSessionPortFactory;
import sessionport.datacomm.group.object.relayed.AnEfficientRelayingObjectGroupSessionPort;




//ReplicatedServerDuplexRPCClientPortSelector.createDuplexRPCPort(aServersDescription, 
//aSessionsServerName, 
//aName,
//ParticipantChoice.CLIENT_ONLY)
// this class no longer needed
public class ALatecomerObjectGroupSessionRelayedPortFactory implements GroupSessionPortFactory<Object> {

	@Override
	public GroupSessionPort<Object> createGroupSessionPort(
			String aSessionsServerHost, String aSessionsServerId,
			String aSessionsServerName, String aSessionName, String anId,
			String aName, ParticipantChoice aJoinChoice) {
		DuplexRPCClientInputPort aSessionsServerInputPort = DuplexRPCInputPortSelector.createDuplexRPCClientInputPort(aSessionsServerHost, 
				aSessionsServerId, aSessionsServerName, aName);		
		return createGroupSessionPort(aSessionsServerInputPort, aSessionName, anId, aName, aJoinChoice);
	}

	@Override
	public GroupSessionPort<Object> createGroupSessionPort(
			DuplexRPCClientInputPort sessionsManagerClientPort,
			String sessionName, String anId, String name, ParticipantChoice aChoice) {
//		return new ALatecomerRelayingObjectGroupSessionPort(sessionsManagerClientPort, sessionName, anId, name, aChoice);
		return new AnEfficientRelayingObjectGroupSessionPort(sessionsManagerClientPort, sessionName, anId, name, aChoice);
	}

}
