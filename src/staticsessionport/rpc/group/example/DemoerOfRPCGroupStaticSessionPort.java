package staticsessionport.rpc.group.example;

import multiserverport.rpc.group.example.AliceGroupRPCMSPLauncher;
import multiserverport.rpc.group.example.BobGroupRPCMSPLauncher;
import multiserverport.rpc.group.example.Server1GroupRPCMSPServerLauncher;
import multiserverport.rpc.group.example.Server2GroupRPCMSPServerLauncher;
import port.sessionserver.relay.late.ALatecomerSessionServerLauncher;
import bus.uigen.models.MainClassLaunchingUtility;
// must click on each dialogue box after all processes have connected
// can probably automate that for testing purposes
// the semantic are weird and depend on what order the ok buttons are executed
// the reason is that the clients do a send on a client port which is a reply and
// so the message goes to the last process from whuich a message was received
// the fact that the connects result in communication among all three parties is what
// is important here, the user interaction is confusing
// as the client code says, race conditions abound
// but it all seems to work good
public class DemoerOfRPCGroupStaticSessionPort {
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
		MainClassLaunchingUtility.interactiveLaunch(classes);
	}	

}
