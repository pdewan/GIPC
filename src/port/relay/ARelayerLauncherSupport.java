package port.relay;

import inputport.rpc.duplex.ADuplexRPCInputPortLauncherSupport;
import port.sessionserver.ASessionServerLauncherSupport;
import port.sessionserver.relay.RelayerClientAndServerSupport;
import port.sessionserver.relay.RelayerObjectSelector;

public class ARelayerLauncherSupport extends ADuplexRPCInputPortLauncherSupport{	
	
	public  void setFactories() {
		super.setFactories();
		RelayerObjectSelector.setRelayerObjectFactory(new ARelayerObjectFactory());
		RelayerClientAndServerSupport.setRelayerServerFactories();
//		SessionServerSelector.setSessionServerFactory(new ARelayerSupportingSessionServerFactory());
//		RelayerSelector.setRelayerFactory(new ARelayerFactory());
	}
	
}
