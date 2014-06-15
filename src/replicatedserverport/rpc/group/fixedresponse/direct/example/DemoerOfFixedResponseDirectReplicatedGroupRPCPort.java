package replicatedserverport.rpc.group.fixedresponse.direct.example;

import replicatedserverport.rpc.group.earliestresponse.direct.example.AliceEarliestResponseP2PReplicatedPortLauncher;
import replicatedserverport.rpc.group.earliestresponse.direct.example.BobEarliestResponseP2PReplicatedPortLauncher;
import replicatedserverport.rpc.group.earliestresponse.direct.example.CathyEarliestResponseP2PReplicatedPortLauncher;
import replicatedserverport.rpc.group.earliestresponse.direct.example.EarliestResponseReplicatedSessionServer1Launcher;
import replicatedserverport.rpc.group.earliestresponse.direct.example.EarliestResponseReplicatedSessionServer2Launcher;
import replicatedserverport.rpc.group.earliestresponse.relayed.latecomer.example.AnEarliestResponseReplicatedLatecomerSessionServer3Launcher;
import bus.uigen.models.MainClassLaunchingUtility;
/* the number of servers can vary from 1 to 3 but they
 * all have to be started before interaction. All of them compute
 * the RPC calls and return results, but a server computes the results of onlt
 * the client mapped to it statically by the sttaic methods of ClientServerMapping
 * called by each server
 * so if the server of a client dies, there is no server to service its leave call or to inform it
 * of new clients
 * actually clients are informed but the words of robert frost message is not received
 * let us dig deep into this
 * ah, that is because it is p2p and each connection triggers the connect message
 * but the join awareness is through session manager
 * but the interaction should continue as this is P2P
 *  The server failure is communicated to clients as exeptions 
 * since this is P2P, the interaction does not stop when all severs go away
 * just that more people cannot join the session
 * so one should incrementally bring up the clients in that case to demo this facility
 * very cool
 */
public class DemoerOfFixedResponseDirectReplicatedGroupRPCPort {
	public static void main(String args[]) {
		demo();
	}
	
	public static void demo() {

		
		Class[] classes = {
				FixedResponseReplicatedServer1Launcher.class,
				FixedResponseReplicatedServer2Launcher.class,
				FixedResponseReplicatedServer3Launcher.class,
				AliceFixedResponseP2PReplicatedPortLauncher.class,
				BobFixedResponseP2PReplicatedPortLauncher.class,
				CathyFixedResponseP2PReplicatedPortLauncher.class
				
		};
		MainClassLaunchingUtility.createInteractiveLauncher(classes);
	}
	
	

}
