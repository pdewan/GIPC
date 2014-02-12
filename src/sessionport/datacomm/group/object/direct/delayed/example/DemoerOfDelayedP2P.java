package sessionport.datacomm.group.object.direct.delayed.example;


import bus.uigen.models.MainClassLaunchingUtility;

public class DemoerOfDelayedP2P {
	public static void main(String args[]) {		
		demo();
	}
	
	public static void demo() {
		Class[] classes = {
				ADelayingSessionPortSessionServerLauncher.class,
				AnAliceDelayingGroupSessionPort.class,
				ABobDelayingGroupSessionPort.class,
				ACathyDelayingGroupSessionPort.class};
		
		MainClassLaunchingUtility.interactiveLaunch(classes);
		
	}

}
