package replicatedsessionport.datacomm.group.object;
import port.ParticipantChoice;
import port.sessionserver.SessionParticipantDescription;
import replicatedsessionport.datacomm.group.ReplicatedGroupSessionPortFactory;
import replicatedsessionport.datacomm.group.object.relayed.latecomer.AReplicatedLatecomerObjectGroupSessionRelayedPortFactory;
import sessionport.datacomm.group.GroupSessionPort;
import sessionport.datacomm.group.object.ObjectGroupSessionPortSelector;
public class ReplicatedObjectGroupSessionPortSelector extends  ObjectGroupSessionPortSelector {
//	static GroupSessionPortFactory<Object> sessionPortFactory = new AnObjectGroupSessionLayeredPortFactory();
	static ReplicatedGroupSessionPortFactory<Object> sessionPortFactory = 
					new AReplicatedLatecomerObjectGroupSessionRelayedPortFactory();

	public static void setReplicatedGroupSessionPortFactory(
			ReplicatedGroupSessionPortFactory<Object> aFactory) {
		sessionPortFactory = aFactory;
	}
	public static GroupSessionPort<Object> createReplicatedObjectGroupSessionPort(
			String aSessionsServerHost, String aSessionsServerId,
			String aSessionsServerName, String aSessionName, String anId,
			String aName, ParticipantChoice aChoice,
			SessionParticipantDescription[] aServersList) {
		return sessionPortFactory.
			createReplicatedGroupSessionPort(aSessionsServerHost, aSessionsServerId, 
				aSessionsServerName, aSessionName, anId, aName, aChoice, aServersList);
	}
//	public static GroupSessionPort<Object> createObjectGroupSessionPort(
//			DuplexRPCClientInputPort aSessionsManagerClientPort,
//			String aSessionName, 
//			String anId, 
//			String aName, ParticipantChoice aJoinChoice) {
//		return sessionPortFactory.
//		            createGroupSessionPort(
//		            		aSessionsManagerClientPort, aSessionName, anId, aName, aJoinChoice);
//	}
}
