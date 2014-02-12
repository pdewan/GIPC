package port.sessionserver.relay;

import port.relay.ARelayerObjectFactory;
import port.sessionserver.ASessionServerLauncherSupport;

public class ASessionServerRelayerLauncherSupport extends ASessionServerLauncherSupport {	
	
	public  void setFactories() {
		super.setFactories();
		RelayerObjectSelector.setRelayerObjectFactory(new ARelayerObjectFactory());
		RelayerClientAndServerSupport.setSessionServerRelayerServerFactories();
//		SessionServerSelector.setSessionServerFactory(new ARelayerSupportingSessionServerFactory());
//		RelayerSelector.setRelayerFactory(new ARelayerFactory());
	}
	
}
