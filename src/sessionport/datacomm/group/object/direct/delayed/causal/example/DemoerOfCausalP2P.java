package sessionport.datacomm.group.object.direct.delayed.causal.example;


import bus.uigen.models.MainClassLaunchingUtility;

public class DemoerOfCausalP2P {
	public static void main(String args[]) {		
		demo();
	}
	
	public static void demo() {
		Class[] classes = {
				ACausalSessionPortSessionServerLauncher.class,
				AnAliceCausalGroupSessionPort.class,
				ABobCausalGroupSessionPort.class,
				ACathyCausalGroupSessionPort.class};
		
		MainClassLaunchingUtility.interactiveLaunch(classes);
		
	}

}
