package staticsessionport.rpc.group.example;

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
	public static void demo() {		
		Class[] classes = {
				
				// all of these are members regaredless of the join choice in the descriptions
				// because port is explicitly created

				AliceGroupRPCSSPLauncher.class, 
				BobGroupRPCSSPLauncher.class, 
				CathyGroupRPCSSPLauncher.class,

				
		};
		MainClassLaunchingUtility.interactiveLaunch(classes);
	}	

}
