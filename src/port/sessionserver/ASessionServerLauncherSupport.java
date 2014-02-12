package port.sessionserver;

import inputport.rpc.duplex.ADuplexRPCInputPortLauncherSupport;

public class ASessionServerLauncherSupport extends ADuplexRPCInputPortLauncherSupport {	
	
	public  void setFactories() {
		super.setFactories();
		setSessionServerFactories();
//		SessionServerSelector.setSessionServerFactory(new ASessionServerFactory());


	}
	public static void setSessionServerFactories() {
		SessionServerSelector.setSessionServerFactory(new ASessionServerFactory());

	}
	
}
