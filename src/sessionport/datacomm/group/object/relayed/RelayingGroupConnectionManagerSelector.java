package sessionport.datacomm.group.object.relayed;

import port.ParticipantChoice;
import sessionport.datacomm.group.GroupSessionPort;

public class RelayingGroupConnectionManagerSelector {
	static RelayingGroupConnectionManagerFactory factory = new ARelayingGroupConnectionManagerFactory();
	
	public static RelayingGroupConnectionManagerFactory getRelayingGroupConnectionManagerFactory() {
		return factory;
	}

	public static void setRelayingGroupConnectionManagerFactory(RelayingGroupConnectionManagerFactory factory) {
		RelayingGroupConnectionManagerSelector.factory = factory;
	}

	public static RelayingGroupConnectionsManager createRelayingGroupConnectionManager
		(GroupSessionPort<Object> aGroupSessionPort, ParticipantChoice aChoice) {
		return factory.createRelayingGroupConnectionManager(aGroupSessionPort, aChoice);
	}
}
