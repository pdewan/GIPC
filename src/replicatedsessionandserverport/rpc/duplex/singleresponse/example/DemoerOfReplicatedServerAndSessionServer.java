package replicatedsessionandserverport.rpc.duplex.singleresponse.example;

import port.ParticipantChoice;
import bus.uigen.models.MainClassLaunchingUtility;
/* wonder how this worked. The replicated servers do nto join session server
 * as down in AnAbstractSimplexBufferVariableServerConnectionsManager is code
 * that does not join if this process is a server only:
 * if (joinChoice == ParticipantChoice.SERVER_ONLY) // do not open connection if server
			return;
  * This seems to be to make sure in p2p case the connections are unidirectional.
  * perhaps this code broke when P2P support was added.
  * maybe a session particppant descriptions hould have an extra flag
  * to disambiguate peerparticipants from session server participants.
  * have changed the code so  if the other party is not a client, they do join now
  * participant choice now is heavily overloaded
  * 
  * it seems tp work
  * Start two latecomer servers
  * start an application server 1
  * start alice
  * say someting
  * starr bob, he has what alice said
  * kill app server 1
  * let alice say something, nothing is echoed
  * start appliation server 2
  * the current latecomer server 1 relays messages to app server 2, and alice's input is processed by the new server 2, which sends it to alice and bob
  * now kill latecomer server 1
  * conversation still continues, as latecomer server 2 has taken over
  * start cathy, latecomer server 2 will send all buffered message to her
  * so this very much is teh finale of fault tolerance
  * sigh but there are some race conditions here - maybe having to do wth
  * the OE frames displayed, but sometimes the server 2 does not replay messages
 */
public class DemoerOfReplicatedServerAndSessionServer {
	public static void main(String args[]) {
		demo();

		
//		Class[] classes = {
//				AReplicatedSingleResponseReplicatedLatecomerSessionServer1Launcher.class,
//				AReplicatedSingleResponseReplicatedLatecomerSessionServer2Launcher.class,
//				AReplicatedServer1SingleResponseGroupSessionServerPortLauncher.class,
//				AReplicatedServer2SingleResponseGroupSessionServerPortLauncher.class,
//				AReplicatedServer3SingleResponseGroupSessionServerPortLauncher.class,
//				AnAliceSingleResponseReplicatedSessionServerClientPortLauncher.class,
//				ABobSingleResponseReplicatedSessionServerClientPortLauncher.class,
//				ACathySingleResponseReplicatedSessionServerClientPortLauncher.class
//		};
//		MainClassListLauncher.launch(classes);

	}
	
	public static void demo() {
//		OEMisc.runWithObjectEditorConsole(AReplicatedSingleResponseReplicatedLatecomerSessionServer1Launcher.class, "");
//		OEMisc.runWithObjectEditorConsole(AReplicatedSingleResponseReplicatedLatecomerSessionServer2Launcher.class, "");
//
//		OEMisc.runWithObjectEditorConsole(AReplicatedServer1SingleResponseGroupSessionServerPortLauncher.class, "");
//		OEMisc.runWithObjectEditorConsole(AReplicatedServer2SingleResponseGroupSessionServerPortLauncher.class, "");
//		OEMisc.runWithObjectEditorConsole(AnAliceSingleResponseReplicatedSessionServerClientPortLauncher, "");
//
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
		
		Class[] classes = {
				AReplicatedSingleResponseReplicatedLatecomerSessionServer1Launcher.class,
				AReplicatedSingleResponseReplicatedLatecomerSessionServer2Launcher.class,
				AReplicatedServer1SingleResponseGroupSessionServerPortLauncher.class,
				AReplicatedServer2SingleResponseGroupSessionServerPortLauncher.class,
				AReplicatedServer3SingleResponseGroupSessionServerPortLauncher.class,
				AliceSingleResponseReplicatedSessionServerClientPortLauncher.class,
				BobSingleResponseReplicatedSessionServerClientPortLauncher.class,
				CathySingleResponseReplicatedSessionServerClientPortLauncher.class
		};
		MainClassLaunchingUtility.createInteractiveLauncher(classes);

	}
	
	

}
