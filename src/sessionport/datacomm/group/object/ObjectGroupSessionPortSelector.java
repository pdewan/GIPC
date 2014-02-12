package sessionport.datacomm.group.object;
import port.ParticipantChoice;
import inputport.rpc.duplex.DuplexRPCClientInputPort;
import sessionport.datacomm.group.GroupSessionPort;
import sessionport.datacomm.group.GroupSessionPortFactory;
import sessionport.datacomm.group.object.relayed.AnObjectGroupSessionPortRelayedFactory;
public class ObjectGroupSessionPortSelector  {
//	static GroupSessionPortFactory<Object> sessionPortFactory = new AnObjectGroupSessionLayeredPortFactory();
	static GroupSessionPortFactory<Object> sessionPortFactory = new AnObjectGroupSessionPortRelayedFactory();

	public static void setGroupSessionPortFactory(GroupSessionPortFactory<Object> aFactory) {
		sessionPortFactory = aFactory;
	}
	public static GroupSessionPort<Object> createObjectGroupSessionPort(
			String aSessionsServerHost, String aSessionsServerId,
			String aSessionsServerName, String aSessionName, String anId,
			String aName, ParticipantChoice aChoice) {
		return sessionPortFactory.createGroupSessionPort(aSessionsServerHost, aSessionsServerId, 
				aSessionsServerName, aSessionName, anId, aName, aChoice);
	}
	public static GroupSessionPort<Object> createObjectGroupSessionPort(
			DuplexRPCClientInputPort aSessionsManagerClientPort,
			String aSessionName, 
			String anId, 
			String aName, ParticipantChoice aJoinChoice) {
		return sessionPortFactory.createGroupSessionPort(aSessionsManagerClientPort, aSessionName, anId, aName, aJoinChoice);
	}
}
