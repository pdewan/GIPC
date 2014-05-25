package replicatedserverport.rpc.groupserver.singleresponse;

import port.ParticipantChoice;
import sessionport.datacomm.group.GroupSessionPort;
import sessionport.datacomm.group.object.relayed.RelayingGroupConnectionManagerFactory;
import sessionport.datacomm.group.object.relayed.RelayingGroupConnectionsManager;

public class ASingleResponseLatecomerRelayingGroupConnectionManagerFactory implements RelayingGroupConnectionManagerFactory{
	
	public RelayingGroupConnectionsManager
			createRelayingGroupConnectionManager(
					GroupSessionPort<Object> aGroupSessionPort, ParticipantChoice aChoice) {
		return new ASingleResponseLatecomerRelayingGroupConnectionsManager(aGroupSessionPort, aChoice);
	}
}
