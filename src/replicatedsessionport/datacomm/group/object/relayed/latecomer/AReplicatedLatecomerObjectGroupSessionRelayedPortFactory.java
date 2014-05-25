package replicatedsessionport.datacomm.group.object.relayed.latecomer;


import inputport.rpc.duplex.DuplexRPCClientInputPort;
import port.ParticipantChoice;
import port.sessionserver.SessionParticipantDescription;
import replicatedserverport.rpc.duplex.ReplicatedServerDuplexRPCClientPortSelector;
import replicatedsessionport.datacomm.group.ReplicatedGroupSessionPortFactory;
import sessionport.datacomm.group.GroupSessionPort;
import sessionport.datacomm.group.object.relayed.latecomer.ALatecomerObjectGroupSessionRelayedPortFactory;




//ReplicatedServerDuplexRPCClientPortSelector.createDuplexRPCPort(aServersDescription, 
//aSessionsServerName, 
//aName,
//ParticipantChoice.CLIENT_ONLY)

public class AReplicatedLatecomerObjectGroupSessionRelayedPortFactory 
       extends ALatecomerObjectGroupSessionRelayedPortFactory
      implements ReplicatedGroupSessionPortFactory<Object> {

	@Override
	public GroupSessionPort<Object> createReplicatedGroupSessionPort(
			String aSessionsServerHost, String aSessionsServerId,
			String aSessionsServerName, String aSessionName, String anId,
			String aName, 
			ParticipantChoice aJoinChoice,
			SessionParticipantDescription[] aServersList) {
//		DuplexRPCClientInputPort aSessionsServerInputPort = DuplexRPCInputPortSelector.createDuplexRPCClientInputPort(aSessionsServerHost, 
//		createDuplexRPCClientInputPort(aSessionsServerHost, 
//				
//		aSessionsServerId, aSessionsServerName, aName);	
		DuplexRPCClientInputPort aSessionsServerInputPort = 
			ReplicatedServerDuplexRPCClientPortSelector.
//			     createDuplexRPCPort(aServersList, anId, aName, aJoinChoice);
		     createDuplexRPCPort(aServersList, aSessionsServerName, aName, aJoinChoice);

			
			
				
		return createGroupSessionPort(aSessionsServerInputPort, 
				aSessionName, 
				anId, 
				aName, 
				aJoinChoice
				);
	}

//	@Override
//	public GroupSessionPort<Object> createReplicatedGroupSessionPort(
//			DuplexRPCClientInputPort sessionsManagerClientPort,
//			String sessionName, String anId, String name, 
//			ParticipantChoice aChoice,
//			ServerPortDescription[] aServersList) {
//		return new ALatecomerRelayingObjectGroupSessionPort(sessionsManagerClientPort, sessionName, anId, name, aChoice);
//	}

}
