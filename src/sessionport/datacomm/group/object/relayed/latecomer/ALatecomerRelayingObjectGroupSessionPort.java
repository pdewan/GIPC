package sessionport.datacomm.group.object.relayed.latecomer;

import port.ParticipantChoice;
import inputport.rpc.duplex.DuplexRPCClientInputPort;
import sessionport.datacomm.duplex.object.relayed.RelayingDuplexConnectionsManager;
import sessionport.datacomm.group.object.relayed.AnEfficientRelayingObjectGroupSessionPort;
// again looks like all we need is simply a differentr connection manager
// this class no longer needed
public class ALatecomerRelayingObjectGroupSessionPort extends AnEfficientRelayingObjectGroupSessionPort{

	public ALatecomerRelayingObjectGroupSessionPort(
			DuplexRPCClientInputPort sessionsServerClientPort,
			String sessionName, String anId, String name, ParticipantChoice aChoice) {
		super(sessionsServerClientPort, sessionName, anId, name, aChoice);
	}
	
	@Override
	protected RelayingDuplexConnectionsManager createConnectionsManager() {
		groupConnectionsManager = new ALatecomerRelayingGroupConnectionsManager(this, participantChoice); // ouch, side effect
		return groupConnectionsManager;
	}

}
