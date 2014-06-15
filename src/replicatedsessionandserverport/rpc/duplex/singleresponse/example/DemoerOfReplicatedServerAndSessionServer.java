package replicatedsessionandserverport.rpc.duplex.singleresponse.example;

import bus.uigen.models.MainClassLaunchingUtility;

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
