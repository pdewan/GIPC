package sessionport.rpc.group.mvc.latecomer.example;

import port.sessionserver.relay.late.ALatecomerSessionServerLauncher;
import bus.uigen.models.MainClassLaunchingUtility;

public class DemoerOfMVCLatecomerRPCGroupSessionPort {
	public static void main(String args[]) {
		demo();
	}	
	public static void demo() {		
		Class[] classes = {

				ALatecomerSessionServerLauncher.class, 
				ALatecomerSessionPortMVCServerLauncher.class,
				AnAliceMVCLatecomerSessionPortLauncher.class,
				ABobLatecomerMVCSessionPortLauncher.class,
				ACathyLatecomerMVCSessionPortLauncher.class,

				
		};
		MainClassLaunchingUtility.interactiveLaunch(classes);
	}	

}
