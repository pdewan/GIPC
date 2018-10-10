package sessionport.datacomm.group.object.relayed.latecomer.example;

import bus.uigen.pipe.MainClassLaunchingUtility;
import sessionport.datacomm.group.object.relayed.example.CathyClientRelayedGroupSessionPort;
import sessionport.datacomm.group.object.relayed.latecomer.ASessionPortLatecomerSupportingSessionServerLauncher;

public class DemoerOfGroupLatecomerRelayedGroupSessionPort {
	public static void main(String args[]) {
		demo();
	}	
	public static void demo() {		
		Class[] classes = {
//				SessionPortSessionServerLauncher.class,
				ASessionPortLatecomerSupportingSessionServerLauncher.class,
				AliceMemberLatecomerGroupSessionPort.class,
				BobServerLatecomerGroupSessionPort.class,
				CathyClientRelayedGroupSessionPort.class, // cathy does no receive buffered messages as a client only
		};
		MainClassLaunchingUtility.createInteractiveLauncher(classes);
	}	

}
