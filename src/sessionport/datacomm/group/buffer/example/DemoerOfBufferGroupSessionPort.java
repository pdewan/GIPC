package sessionport.datacomm.group.buffer.example;

import bus.uigen.pipe.MainClassLaunchingUtility;
import port.sessionserver.relay.late.ALatecomerSessionServerLauncher;
// the words of robert forst are misleading, this program does not accept any input
// acting only at connection time
public class DemoerOfBufferGroupSessionPort {
	public static void main(String args[]) {
		demo();
	}	
	public static void demo() {		
		Class[] classes = {
//				SessionPortSessionServerLauncher.class,
				ALatecomerSessionServerLauncher.class,

				AliceBufferGroupSessionPort.class,
				BobBufferGroupSessionPort.class,
				CathyBufferGroupSessionPort.class 
		};
		MainClassLaunchingUtility.createInteractiveLauncher(classes);
	}	

}
