package port.sessionserver.relay.late;

import port.sessionserver.ASessionServerLauncherSupport;

public class ALatecomerServerLauncherSupport extends ASessionServerLauncherSupport {	
	
	public  void setFactories() {
		super.setFactories();
		LatecomerClientAndServerUtil.setServerLatecomerFactories();
//		SessionServerSelector.setSessionServerFactory(new ARelayerSupportingSessionServerFactory());
//		RelayerSelector.setRelayerFactory(new ARelayerFactory());
	}
	
//	public void setTracing() {
//		Tracer.showInfo(true);
//		Tracer.setKeyWordStatus(Tracer.ALL_KEYWORDS, false);
//		Tracer.setKeyWordStatus(ADuplexBufferInputPortLauncherSupport.class, true);
//	}
	
}
