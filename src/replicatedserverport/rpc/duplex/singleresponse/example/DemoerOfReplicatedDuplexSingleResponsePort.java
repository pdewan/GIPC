package replicatedserverport.rpc.duplex.singleresponse.example;

import bus.uigen.models.MainClassLaunchingUtility;
/* the number of servers can vary from 1 to 3 but they
 * all have to be started before interaction. All of them compute
 * the RPC calls, but only one of them returns results
 * however, there is fault tolerance, as long as there is a server the
 * dialogue goes on. The server failire is communicated to clients as exeptions
 * This is duplex because each server sends results back to only its caller, I suppose the
 * communication goes directly.Will have to check. Perhaps because there is no group call
 * if results are sent back, they are done individually not ina  group call
 */
public class DemoerOfReplicatedDuplexSingleResponsePort {
	public static void main(String args[]) {
		demo();
	}
	
	public static void demo() {

		
		Class[] classes = {
				AGroupRPCSingleReponseServer1Launcher.class,
				AGroupRPCSingleResponseServer2Launcher.class,
				AGroupRPCSingleResponseServer3Launcher.class,
				AliceSingleResponseGroupRPCClientPortLauncher.class,
				BobSingleResponseGroupRPCClientPortLauncher.class,
				CathySingleResponseGroupRPCClientPortLauncher.class
				
		};
		MainClassLaunchingUtility.interactiveLaunch(classes);
	}
	
	

}
