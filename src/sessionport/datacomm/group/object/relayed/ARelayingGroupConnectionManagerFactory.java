package sessionport.datacomm.group.object.relayed;

import port.ParticipantChoice;
import sessionport.datacomm.group.GroupSessionPort;

public class ARelayingGroupConnectionManagerFactory implements RelayingGroupConnectionManagerFactory{
	
	public RelayingGroupConnectionsManager
			createRelayingGroupConnectionManager(
					GroupSessionPort<Object> aGroupSessionPort, ParticipantChoice aChoice) {
		return new ARelayingGroupConnectionsManager(aGroupSessionPort, aChoice);
	}
}
