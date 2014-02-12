package port.sessionserver.relay.late;

import sessionport.datacomm.duplex.object.ObjectDuplexSessionPortSelector;
import sessionport.datacomm.duplex.object.relayed.ARelayingObjectDuplexSessionPortFactory;
import sessionport.datacomm.group.object.ObjectGroupSessionPortSelector;
import sessionport.datacomm.group.object.relayed.RelayingGroupConnectionManagerSelector;
import sessionport.datacomm.group.object.relayed.latecomer.ALatecomerObjectGroupSessionRelayedPortFactory;
import sessionport.datacomm.group.object.relayed.latecomer.ALatecomerRelayingGroupConnectionManagerFactory;

public class LatecomerClientAndServerUtil {

	public static void setClientLatecomerFactories() {
			ObjectDuplexSessionPortSelector.setDuplexSessionPortFactory(
					new ARelayingObjectDuplexSessionPortFactory());
			// this is not really needed now is it?
			ObjectGroupSessionPortSelector.setGroupSessionPortFactory(
					new ALatecomerObjectGroupSessionRelayedPortFactory());	
			RelayingGroupConnectionManagerSelector.setRelayingGroupConnectionManagerFactory(new ALatecomerRelayingGroupConnectionManagerFactory());
			
		
	}

	public static void setServerLatecomerFactories() {
		LatecomerRelayerObjectSelector.setRelayerObjectFactory(new ALatecomerRelayerObjectFactory());
		LatecomerRelayerAndSessionServerSelector.setLatecomerRelayerAndSessionServerFactory(new ALatecomerRelayerAndSessionServerFactory());
	
	}

}
