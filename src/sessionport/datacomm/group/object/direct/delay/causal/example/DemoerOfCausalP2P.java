package sessionport.datacomm.group.object.direct.delay.causal.example;


import port.sessionserver.ASessionServerLauncher;
import bus.uigen.pipe.MainClassLaunchingUtility;

public class DemoerOfCausalP2P {
	public static void main(String args[]) {		
		demo();
	}
	
	public static void demo() {
		Class[] classes = {
				// we do not need a special server for causal
//				ACausalSessionPortSessionServerLauncher.class,
				ASessionServerLauncher.class,
				AliceCausalGroupSessionPort.class,
				BobCausalGroupSessionPort.class,
				CathyCausalGroupSessionPort.class};
		
		MainClassLaunchingUtility.createInteractiveLauncher(classes);
		
	}

}
