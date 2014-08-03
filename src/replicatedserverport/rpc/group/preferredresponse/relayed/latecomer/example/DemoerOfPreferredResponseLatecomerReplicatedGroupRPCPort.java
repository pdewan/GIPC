package replicatedserverport.rpc.group.preferredresponse.relayed.latecomer.example;

import bus.uigen.pipes.MainClassLaunchingUtility;
/* the number of servers can vary from 1 to 3 but they
 * all have to be started before interaction. All of them compute
 * the RPC calls and return results, but only one of the results is communicated.
 * however, there is fault tolerance, as long as there is a server the
 * dialogue goes on. The server failure is communicated to clients as exeptions
 * Each client has a preferred server  but it it goes down, some other server
 * is chosen.The ClientServerMapping is the one used to decide on preferred server
 * imagine this library being replaced by a server, server thatd ynamically decides the
 * allocation
 * 
 * cathy gets events with variable delay - jitter. But in the console based demo
 * I could not reproduce it. have to investigate further
 *  
 * 
 */
public class DemoerOfPreferredResponseLatecomerReplicatedGroupRPCPort {
	public static void main(String args[]) {
		demo();
	}
	
	public static void demo() {

		
		Class[] classes = {
				APreferredResponseReplicatedLatecomerSessionServer1Launcher.class,
				APreferredResponseReplicatedLatecomerSessionServer2Launcher.class,
				APreferredResponseReplicatedLatecomerSessionServer3Launcher.class,
				AliceJitteryPreferredResponseLatecomerReplicatedPortLauncher.class,
				BobJitteryPreferredResponseLatecomerReplicatedPortLauncher.class,
				CathyJitteryPreferredResponseLatecomerReplicatedPortLauncher.class
				
		};
		MainClassLaunchingUtility.createInteractiveLauncher(classes);
	}
	
	

}
