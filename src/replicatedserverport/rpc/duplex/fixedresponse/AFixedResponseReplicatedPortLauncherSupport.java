package replicatedserverport.rpc.duplex.fixedresponse;

import replicatedserverport.datacomm.duplex.AReplicatedPortLauncherSupport;

public class AFixedResponseReplicatedPortLauncherSupport extends AReplicatedPortLauncherSupport {	
	
     public  void setFactories() {
		super.setFactories();
		FixedlResponseReplicatedClientServerSupport.setLocalResponseClientFactories();
//		setGroupObjectTrapper();
		
//		ReplicatedServerObjectTrapperSelector.getTrapperSelector().setReceiveTrapperFactory(
//				new AReceiveMessageForwarderFactory());

//		LatecomerRelayerAndSessionServerSelector.setLatecomerRelayerAndSessionServerFactory(new ALatecomerRelayerAndSessionServerFactory());
		
//		ALatecomerRelayerAndSessionServerLauncherSupport.setLatecomerRelayedCommunicaton(true);
//		LatecomerClientAndServerSupport.setClientLatecomerFactories();

//		GlobalState.setAnyCast(true);
//		GlobalDelayState.setDelayClientBufferSends(true);
//		GroupSingleResponseSetter.supportSingleResponse(serverInputPort);

	}
}
