package sessionport.datacomm.group.object.relayed.latecomer;

import port.sessionserver.relay.late.LatecomerClientAndServerUtil;
import inputport.rpc.group.AGroupRPCInputPortLauncherSupport;

public class AnObjectGroupSessionPortLatecomerLauncherSupport extends AGroupRPCInputPortLauncherSupport {
	public  void setFactories() {
		super.setFactories();
		LatecomerClientAndServerUtil.setClientLatecomerFactories();
//		RelayerClientAndServerSupport.setClientEfficientRelayedCommunicaton();

	}
}
