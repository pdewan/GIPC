package sessionport.datacomm.group.object.relayed.latecomer;

import inputport.rpc.group.AGroupRPCInputPortLauncherSupport;
import port.sessionserver.relay.late.LatecomerClientAndServerUtil;

public class AnObjectGroupSessionPortLatecomerLauncherSupport extends AGroupRPCInputPortLauncherSupport {
	public  void setFactories() {
		super.setFactories();
		LatecomerClientAndServerUtil.setClientLatecomerFactories();
//		RelayerClientAndServerSupport.setClientEfficientRelayedCommunicaton();

	}
}
