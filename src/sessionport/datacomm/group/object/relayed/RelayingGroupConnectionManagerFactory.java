package sessionport.datacomm.group.object.relayed;

import port.ParticipantChoice;
import sessionport.datacomm.group.GroupSessionPort;

public interface RelayingGroupConnectionManagerFactory {
	
	RelayingGroupConnectionsManager createRelayingGroupConnectionManager
		(GroupSessionPort<Object> aGroupSessionPort, ParticipantChoice aChoice);
}
