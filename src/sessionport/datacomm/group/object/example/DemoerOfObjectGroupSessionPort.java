package sessionport.datacomm.group.object.example;

import bus.uigen.pipe.MainClassLaunchingUtility;
import port.sessionserver.relay.late.ALatecomerSessionServerLauncher;
// the words of robert forst are misleading, this program does not accept any input
// acting only at connection time
public class DemoerOfObjectGroupSessionPort {
	public static void main(String args[]) {
		demo();
	}	
	public static void demo() {		
		Class[] classes = {
//				SessionPortSessionServerLauncher.class,
				ALatecomerSessionServerLauncher.class,

				AliceObjectGroupSessionPort.class,
				BobObjectGroupSessionPort.class,
				CathyObjectGroupSessionPort.class 
		};
		MainClassLaunchingUtility.createInteractiveLauncher(classes);
	}	

}
