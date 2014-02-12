package sessionport.datacomm.group.object.relayed.latecomer;

import port.ParticipantChoice;
import sessionport.datacomm.group.GroupSessionPort;
import sessionport.datacomm.group.object.relayed.RelayingGroupConnectionManagerFactory;
import sessionport.datacomm.group.object.relayed.RelayingGroupConnectionsManager;

public class ALatecomerRelayingGroupConnectionManagerFactory implements RelayingGroupConnectionManagerFactory{
	
	public RelayingGroupConnectionsManager
			createRelayingGroupConnectionManager(
					GroupSessionPort<Object> aGroupSessionPort, ParticipantChoice aChoice) {
		return new ALatecomerRelayingGroupConnectionsManager(aGroupSessionPort, aChoice);
	}
}
