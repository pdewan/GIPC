package sessionport.rpc.group.mvc.direct.example;

import port.sessionserver.ASessionServerLauncher;
import bus.uigen.models.MainClassLaunchingUtility;

public class DemoerOfMVCDirecRPCGroupSessionPort {
	public static void main(String args[]) {
		demo();
	}	
	public static void demo() {		
		Class[] classes = {
//				SessionPortSessionServerLauncher.class,
//				ASessionPortLatecomerSupportingSessionServerLauncher.class,
				ASessionServerLauncher.class, // this is direct, so why use the other more complicated session servers
				ADirectSessionPortMVCServerLauncher.class,
				AliceMVCDirectSessionPortLauncher.class,
				BobMVCDirectSessionPortLauncher.class,
				CathyMVCDirectSessionPortLauncher.class,
				
		};
		MainClassLaunchingUtility.interactiveLaunch(classes);
	}	

}
