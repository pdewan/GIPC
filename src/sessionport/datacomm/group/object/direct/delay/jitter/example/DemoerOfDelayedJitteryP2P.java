package sessionport.datacomm.group.object.direct.delay.jitter.example;


import port.sessionserver.ASessionServerLauncher;
import bus.uigen.models.MainClassLaunchingUtility;

public class DemoerOfDelayedJitteryP2P {
	public static void main(String args[]) {		
		demo();
	}
	
	public static void demo() {
		Class[] classes = {
				AJitterySessionPortSessionServerLauncher.class, // do we need this server, yes it ha sa special support?
				AliceJitteryGroupSessionPort.class,
				BobJitteryGroupSessionPort.class,
				CathyJitteryGroupSessionPort.class};
		
		MainClassLaunchingUtility.interactiveLaunch(classes);
		
	}

}
