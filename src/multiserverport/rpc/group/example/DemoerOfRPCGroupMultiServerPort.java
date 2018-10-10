package multiserverport.rpc.group.example;

import bus.uigen.pipe.MainClassLaunchingUtility;

public class DemoerOfRPCGroupMultiServerPort {
	public static void main(String args[]) {
		demo();
	}	
	/*
	 * This is much changed fromw what was there before, which probably was all wrong.
	 * The clients join as client only and not symmetric join as they do not serve methods and also
	 * as they do not have ids, which even the original did not provide. They also do not register 
	 * remote objects.
	 */
	public static void demo() {		
		Class[] classes = {
				
				// all of the clients are clients regaredless of the join choice in the descriptions
				// because port is explicitly created

				Server1GroupRPCMSPServerLauncher.class, 
				Server2GroupRPCMSPServerLauncher.class,
				AliceGroupRPCMSPLauncher.class, 
				BobGroupRPCMSPLauncher.class,

				
		};
		MainClassLaunchingUtility.createInteractiveLauncher(classes);
	}	

}
