package sessionport.datacomm.group.object.relayed;


import inputport.rpc.duplex.DuplexRPCClientInputPort;
import port.ParticipantChoice;
import sessionport.datacomm.duplex.DuplexSessionPort;
import sessionport.datacomm.duplex.object.ObjectDuplexSessionPortSelector;
import sessionport.datacomm.group.GroupSessionPort;
import sessionport.datacomm.group.GroupSessionPortFactory;
import sessionport.datacomm.group.object.AnObjectGroupSessionPort;






public class AnObjectGroupSessionPortRelayedFactory implements GroupSessionPortFactory<Object> {

	@Override
	public GroupSessionPort<Object> createGroupSessionPort(
			String aSessionsServerHost, String aSessionsServerId,
			String aSessionsServerName, String aSessionName, String anId,
			String aName, ParticipantChoice aJoinChoice) {
		DuplexSessionPort<Object> duplexObjectSessionPort = ObjectDuplexSessionPortSelector.createObjectDuplexSessionPort(aSessionsServerHost, aSessionsServerId, aSessionsServerName, aSessionName, anId, aName, aJoinChoice);
		return new  AnObjectGroupSessionPort(duplexObjectSessionPort, null);

	}

	@Override
	public GroupSessionPort<Object> createGroupSessionPort(
			DuplexRPCClientInputPort aSessionsManagerClienPort,
			String aSessionName, String anId, String aName, ParticipantChoice aChoice) {
		DuplexSessionPort<Object> duplexObjectSessionPort = ObjectDuplexSessionPortSelector.createDuplexSessionPort(aSessionsManagerClienPort, aSessionName, anId, aName, aChoice);
		return new  AnObjectGroupSessionPort(duplexObjectSessionPort, null);
	}
	
}
