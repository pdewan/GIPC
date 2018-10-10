package sessionport.datacomm.group.object.flexible.example;

import bus.uigen.pipe.MainClassLaunchingUtility;
import port.sessionserver.relay.late.ALatecomerSessionServerLauncher;

public class DemoerOfFlexibleGroupObjectSessionPort {
	public static void main(String args[]) {
		demo();
	}	
	public static void demo() {		
		Class[] classes = {
				ALatecomerSessionServerLauncher.class, // can also have relayer
				AnAliceGroupSessionPort.class,
				ABobGroupSessionPort.class,
				ACathyGroupSessionPort.class 
		};
		MainClassLaunchingUtility.createInteractiveLauncher(classes);
	}	

}
