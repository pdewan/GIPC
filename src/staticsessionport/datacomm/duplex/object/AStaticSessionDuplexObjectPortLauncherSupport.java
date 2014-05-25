package staticsessionport.datacomm.duplex.object;

import inputport.datacomm.duplex.object.ADuplexObjectInputPortLauncherSupport;
import port.PortLauncherSupport;
import port.sessionserver.relay.RelayerClientAndServerSupport;

public class AStaticSessionDuplexObjectPortLauncherSupport extends ADuplexObjectInputPortLauncherSupport implements PortLauncherSupport {
	public  void setFactories() {
		super.setFactories();
		RelayerClientAndServerSupport.setRelayedCommunicaton(false);
		
	}
	
}
