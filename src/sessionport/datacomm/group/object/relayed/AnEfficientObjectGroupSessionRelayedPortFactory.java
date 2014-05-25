package sessionport.datacomm.group.object.relayed;


import inputport.rpc.duplex.DuplexRPCClientInputPort;
import inputport.rpc.duplex.DuplexRPCInputPortSelector;
import port.ParticipantChoice;
import sessionport.datacomm.group.GroupSessionPort;
import sessionport.datacomm.group.GroupSessionPortFactory;




// looks like all we need is am efficient conection manager factory instead of this one

public class AnEfficientObjectGroupSessionRelayedPortFactory implements GroupSessionPortFactory<Object> {

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
		return new AnEfficientRelayingObjectGroupSessionPort(sessionsManagerClientPort, sessionName, anId, name, aChoice);
	}

}
