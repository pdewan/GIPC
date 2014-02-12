package port.sessionserver.relay.late;

import inputport.rpc.group.AGroupRPCInputPortLauncherSupport;
import sessionport.datacomm.duplex.object.ObjectDuplexSessionPortSelector;
import sessionport.datacomm.duplex.object.direct.ADirectObjectDuplexSessionPortFactory;
import sessionport.datacomm.duplex.object.relayed.ARelayingObjectDuplexSessionPortFactory;
import sessionport.datacomm.group.object.ObjectGroupSessionPortSelector;
import sessionport.datacomm.group.object.direct.ALayeredObjectGroupSessionPortFactory;
import sessionport.datacomm.group.object.relayed.AnEfficientObjectGroupSessionRelayedPortFactory;
import sessionport.datacomm.group.object.relayed.RelayingGroupConnectionManagerSelector;
import sessionport.datacomm.group.object.relayed.latecomer.ALatecomerObjectGroupSessionRelayedPortFactory;
import sessionport.datacomm.group.object.relayed.latecomer.ALatecomerRelayingGroupConnectionManagerFactory;
import sessionport.datacomm.group.object.relayed.latecomer.ALatecomerRelayingGroupConnectionsManager;

public class ALatecomerRelayerAndSessionServerLauncherSupport extends AGroupRPCInputPortLauncherSupport {	
public  void setFactories() {
		super.setFactories();
//		SingleResponseReplicatedClientServerUtlity.setSingleResponseGroupServerFactories();
		
//		ReplicatedServerObjectTrapperSelector.getTrapperSelector().setReceiveTrapperFactory(
//				new AReceiveMessageForwarderFactory());
		LatecomerClientAndServerUtil.setServerLatecomerFactories();
//		LatecomerRelayerAndSessionServerSelector.setLatecomerRelayerAndSessionServerFactory(new ALatecomerRelayerAndSessionServerFactory());
		
//		ALatecomerRelayerAndSessionServerLauncherSupport.setLatecomerRelayedCommunicaton(true);
		LatecomerClientAndServerUtil.setClientLatecomerFactories();

//		GlobalState.setAnyCast(true);
//		DelayUtlity.setDelayClientBufferSends(true);
//		GroupSingleResponseSetter.supportSingleResponse(serverInputPort);

	}
	public static void setLatecomerRelayedCommunicaton(boolean yes) {
		if (yes) {
			ObjectDuplexSessionPortSelector.setDuplexSessionPortFactory(
					new ARelayingObjectDuplexSessionPortFactory());
//			ObjectGroupSessionPortSelector.setGroupSessionPortFactory(
//					new ALatecomerObjectGroupSessionRelayedPortFactory());	
			ObjectGroupSessionPortSelector.setGroupSessionPortFactory(
			new AnEfficientObjectGroupSessionRelayedPortFactory());	
			RelayingGroupConnectionManagerSelector.setRelayingGroupConnectionManagerFactory(
					new ALatecomerRelayingGroupConnectionManagerFactory());
			
		} else {
			ObjectDuplexSessionPortSelector.setDuplexSessionPortFactory(
					new ADirectObjectDuplexSessionPortFactory());
			ObjectGroupSessionPortSelector.setGroupSessionPortFactory(
					new ALayeredObjectGroupSessionPortFactory());
			
		}		
	}
}
