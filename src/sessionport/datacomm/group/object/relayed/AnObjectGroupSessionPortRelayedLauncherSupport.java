package sessionport.datacomm.group.object.relayed;

import inputport.rpc.group.AGroupRPCInputPortLauncherSupport;
import port.sessionserver.relay.RelayerClientAndServerSupport;

public class AnObjectGroupSessionPortRelayedLauncherSupport extends AGroupRPCInputPortLauncherSupport {	
	

	public  void setFactories() {
		super.setFactories();		
		RelayerClientAndServerSupport.setClientEfficientRelayerFactories();
//		GlobalDelayState.setDelayClientBufferSends(true);

	}
}
