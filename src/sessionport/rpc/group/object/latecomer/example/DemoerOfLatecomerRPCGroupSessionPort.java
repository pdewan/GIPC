package sessionport.rpc.group.object.latecomer.example;

import port.sessionserver.relay.late.ALatecomerSessionServerLauncher;
import bus.uigen.models.MainClassLaunchingUtility;

public class DemoerOfLatecomerRPCGroupSessionPort {
	public static void main(String args[]) {
		demo();
	}	
	public static void demo() {		
		Class[] classes = {

				ALatecomerSessionServerLauncher.class, 
				AliceMemberLatecomerGroupSessionPort.class,
				CathyClientLatecomerGroupSessionPort.class,
				BobServerLatecomerGroupSessionPort.class,

				
		};
		MainClassLaunchingUtility.createInteractiveLauncher(classes);
	}	

}
