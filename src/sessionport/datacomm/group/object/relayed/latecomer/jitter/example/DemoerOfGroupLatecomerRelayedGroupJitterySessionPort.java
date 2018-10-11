package sessionport.datacomm.group.object.relayed.latecomer.jitter.example;

import bus.uigen.pipe.MainClassLaunchingUtility;
import sessionport.datacomm.group.object.relayed.latecomer.jitter.AJitterySessionPortLatecomerSupportingSessionServerLauncher;
// Object editor ofren hangs when interacting with Alice, maybe ecause of new layout of console or setting of cursor
// Have put tracing on in Alice launcher but could not reproduce problem with it
public class DemoerOfGroupLatecomerRelayedGroupJitterySessionPort {
	public static void main(String args[]) {
		demo();
	}	
	public static void demo() {		
		Class[] classes = {
//				SessionPortSessionServerLauncher.class,
				AJitterySessionPortLatecomerSupportingSessionServerLauncher.class,
				AliceJitteryLatecomerGroupSessionPortLauncher.class,
				BobJitteryLatecomerGroupSessionPortLauncher.class,
				CathyJitteryLateGroupSessionPortLauncher.class, 
		};
		MainClassLaunchingUtility.createInteractiveLauncher(classes);
	}	

}
