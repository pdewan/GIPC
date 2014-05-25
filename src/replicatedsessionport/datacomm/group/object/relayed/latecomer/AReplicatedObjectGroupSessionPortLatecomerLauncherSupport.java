package replicatedsessionport.datacomm.group.object.relayed.latecomer;

import port.sessionserver.relay.late.LatecomerClientAndServerUtil;
import replicatedsessionport.datacomm.group.object.ReplicatedObjectGroupSessionPortSelector;
import sessionport.datacomm.group.object.relayed.latecomer.AnObjectGroupSessionPortLatecomerLauncherSupport;

public class AReplicatedObjectGroupSessionPortLatecomerLauncherSupport extends AnObjectGroupSessionPortLatecomerLauncherSupport {
	public  void setFactories() {
		super.setFactories();
		ReplicatedObjectGroupSessionPortSelector.setReplicatedGroupSessionPortFactory(
				new AReplicatedLatecomerObjectGroupSessionRelayedPortFactory());		
		LatecomerClientAndServerUtil.setClientLatecomerFactories();
//		RelayerClientAndServerSupport.setClientEfficientRelayedCommunicaton();

	}
}
