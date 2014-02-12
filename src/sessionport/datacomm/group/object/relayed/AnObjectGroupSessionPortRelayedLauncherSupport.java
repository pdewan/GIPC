package sessionport.datacomm.group.object.relayed;

import port.sessionserver.relay.RelayerClientAndServerSupport;
import inputport.rpc.group.AGroupRPCInputPortLauncherSupport;

public class AnObjectGroupSessionPortRelayedLauncherSupport extends AGroupRPCInputPortLauncherSupport {	
	

	public  void setFactories() {
		super.setFactories();		
		RelayerClientAndServerSupport.setClientEfficientRelayerFactories();
//		GlobalDelayState.setDelayClientBufferSends(true);

	}
}
