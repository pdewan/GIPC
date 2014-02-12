package port.sessionserver.relay;

import port.relay.ARelayerPortFactory;
import port.relay.RelayerPortSelector;
import port.sessionserver.SessionServerSelector;
import sessionport.datacomm.duplex.object.ObjectDuplexSessionPortSelector;
import sessionport.datacomm.duplex.object.direct.ADirectObjectDuplexSessionPortFactory;
import sessionport.datacomm.duplex.object.relayed.ARelayingObjectDuplexSessionPortFactory;
import sessionport.datacomm.group.object.ObjectGroupSessionPortSelector;
import sessionport.datacomm.group.object.direct.ALayeredObjectGroupSessionPortFactory;
import sessionport.datacomm.group.object.relayed.AnEfficientObjectGroupSessionRelayedPortFactory;
import sessionport.datacomm.group.object.relayed.AnObjectGroupSessionPortRelayedFactory;

public class RelayerClientAndServerSupport {

	public static void setSessionServerRelayerServerFactories() {
		SessionServerSelector.setSessionServerFactory(new ARelayerSupportingSessionServerFactory());
		SessionServerRelayerPortSelector.setRelayerFactory(new ASessionServerRelayerPortFactory());
	}
	public static void setRelayerServerFactories() {
//		SessionServerSelector.setSessionServerFactory(new ARelayerSupportingSessionServerFactory());
		RelayerPortSelector.setRelayerFactory(new ARelayerPortFactory());
	}

	public static void setClientEfficientRelayerFactories() {
		
			ObjectDuplexSessionPortSelector.setDuplexSessionPortFactory(
					new ARelayingObjectDuplexSessionPortFactory());
			ObjectGroupSessionPortSelector.setGroupSessionPortFactory(
					new AnEfficientObjectGroupSessionRelayedPortFactory());		
			
		
	}

	public static void setClientRelayerFactories() {	
			ObjectDuplexSessionPortSelector.setDuplexSessionPortFactory(
					new ARelayingObjectDuplexSessionPortFactory());
			ObjectGroupSessionPortSelector.setGroupSessionPortFactory(
					new AnObjectGroupSessionPortRelayedFactory());			
	}

	public static void setEfficientRelayedCommunicaton(boolean yes) {
		if (yes) {
			ObjectDuplexSessionPortSelector.setDuplexSessionPortFactory(
					new ARelayingObjectDuplexSessionPortFactory());
			ObjectGroupSessionPortSelector.setGroupSessionPortFactory(
					new AnEfficientObjectGroupSessionRelayedPortFactory());		
			
		} else {
			ObjectDuplexSessionPortSelector.setDuplexSessionPortFactory(
					new ADirectObjectDuplexSessionPortFactory());
			ObjectGroupSessionPortSelector.setGroupSessionPortFactory(
					new ALayeredObjectGroupSessionPortFactory());
			
		}		
	}

	public static void setRelayedCommunicaton(boolean yes) {
		if (yes) {
			ObjectDuplexSessionPortSelector.setDuplexSessionPortFactory(
					new ARelayingObjectDuplexSessionPortFactory());
			ObjectGroupSessionPortSelector.setGroupSessionPortFactory(
					new AnObjectGroupSessionPortRelayedFactory());		
			
		} else {
			ObjectDuplexSessionPortSelector.setDuplexSessionPortFactory(
					new ADirectObjectDuplexSessionPortFactory());
			ObjectGroupSessionPortSelector.setGroupSessionPortFactory(
					new ALayeredObjectGroupSessionPortFactory());
			
		}		
	}

}
