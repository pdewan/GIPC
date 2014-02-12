package staticsessionport.datacomm.duplex.object;

import port.PortLauncherSupport;
import port.sessionserver.relay.RelayerClientAndServerSupport;
import inputport.datacomm.duplex.object.ADuplexObjectInputPortLauncherSupport;

public class AStaticSessionDuplexObjectPortLauncherSupport extends ADuplexObjectInputPortLauncherSupport implements PortLauncherSupport {
	public  void setFactories() {
		super.setFactories();
		RelayerClientAndServerSupport.setRelayedCommunicaton(false);
		
	}
	
}
