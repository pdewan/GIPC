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
				AnAliceSingleResponseReplicatedSessionServerClientPortLauncher.class,
				ABobSingleResponseReplicatedSessionServerClientPortLauncher.class,
				ACathySingleResponseReplicatedSessionServerClientPortLauncher.class
		};
		MainClassLaunchingUtility.createInteractiveLauncher(classes);

	}
	
	

}
