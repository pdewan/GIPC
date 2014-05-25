package replicatedsessionport.rpc.duplex;

import inputport.datacomm.duplex.DuplexClientInputPort;
import inputport.datacomm.duplex.DuplexServerInputPort;
import inputport.rpc.duplex.ADuplexRPCInputPortFactory;
import inputport.rpc.duplex.DuplexRPCClientInputPort;
import inputport.rpc.duplex.DuplexRPCServerInputPort;
import inputport.rpc.group.GroupRPCServerInputPort;
import port.ParticipantChoice;
import port.sessionserver.SessionParticipantDescription;
import replicatedserverport.rpc.duplex.AReplicatedServerDuplexRPCClientPort;
import replicatedsessionport.datacomm.duplex.object.ReplicatedSessionDuplexObjectPortSelector;
import replicatedsessionport.rpc.group.ReplicatedGroupRPCSessionPortSelector;
import sessionport.rpc.group.GroupRPCSessionPortSelector;

public class AReplicatedSessionDuplexRPCPortFactory extends ADuplexRPCInputPortFactory implements ReplicatedSessionDuplexRPCPortFactory {	
	
	public AReplicatedSessionDuplexRPCPortFactory() {
		super();
	}
	
	@Override
	public GroupRPCServerInputPort createReplicatedSessionGroupRPCServerInputPort(
			String aSessionsServerHost, String aSessionsServerId,
			String aSessionsServerName, String aSessionName, String anId,
			String aName, SessionParticipantDescription[] aServersDescription) {
//		GroupRPCServerInputPort groupPort = 
//			GroupRPCSessionPortSelector.createGroupRPCSessionPort
//			(aSessionsServerHost, aSessionsServerId, aSessionsServerName, aSessionName, anId, aName, ParticipantChoice.SERVER_ONLY);
		GroupRPCServerInputPort groupPort = null;
		if (aServersDescription == null)
		groupPort = 
				GroupRPCSessionPortSelector.createGroupRPCSessionPort
				(aSessionsServerHost, aSessionsServerId, aSessionsServerName, aSessionName, anId, aName, ParticipantChoice.SERVER_ONLY);
		else
			groupPort = ReplicatedGroupRPCSessionPortSelector.createReplicatedGroupRPCSessionPort(
			aSessionsServerHost, aSessionsServerId, aSessionsServerName, aSessionName, anId, aName, ParticipantChoice.SERVER_ONLY, aServersDescription);
				
		return groupPort;

	}

	@Override
	public DuplexRPCServerInputPort createReplicatedSessionDuplexRPCServerInputPort(
			String aSessionsServerHost, String aSessionsServerId,
			String aSessionsServerName, String aSessionName, String anId,
			String aName, SessionParticipantDescription[] aServersDescription) {
		DuplexServerInputPort<Object> duplexPort = 
				ReplicatedSessionDuplexObjectPortSelector.createDuplexReplicatedSessionServerInputPort(aSessionsServerHost, aSessionsServerId, aSessionsServerName, aSessionName, anId, aName, aServersDescription);
		return createDuplexRPCServerInputPort(duplexPort);

	}
	// againthis could take extra arguments to support replicated latecomer session, these would be like the launcher support arguments
	@Override
	public DuplexRPCClientInputPort createReplicatedSessionDuplexRPCClientInputPort(
			String aSessionsServerHost, String aSessionsServerId,
			String aSessionsServerName, String aSessionName,
			String aLogicalRemoteEndPoint, String aName, SessionParticipantDescription[] aServersDescription) {
			DuplexClientInputPort<Object> duplexPort = 	ReplicatedSessionDuplexObjectPortSelector.createDuplexReplicatedSessionClientInputPort(aSessionsServerHost, aSessionsServerId, aSessionsServerName, aSessionName, aLogicalRemoteEndPoint, aName, aServersDescription);
			return new AReplicatedServerDuplexRPCClientPort(duplexPort);


	}
}
