package sessionport.datacomm.group.object.direct.delay.example;


import bus.uigen.pipe.MainClassLaunchingUtility;
import port.sessionserver.ASessionServerLauncher;

public class DemoerOfDelayedP2P {
	public static void main(String args[]) {		
		demo();
	}
	
	public static void demo() {
		Class[] classes = {
				ASessionServerLauncher.class,
				AliceDelayingGroupSessionPort.class,
				BobDelayingGroupSessionPort.class,
				CathyDelayingGroupSessionPort.class};
		
		MainClassLaunchingUtility.createInteractiveLauncher(classes);
		
	}

}
