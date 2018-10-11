package port.demos;

import bus.uigen.pipe.MainClassLaunchingUtility;
import replicatedsessionandserverport.rpc.duplex.singleresponse.example.AReplicatedServer1SingleResponseGroupSessionServerPortLauncher;
import replicatedsessionandserverport.rpc.duplex.singleresponse.example.AReplicatedServer2SingleResponseGroupSessionServerPortLauncher;
import replicatedsessionandserverport.rpc.duplex.singleresponse.example.AReplicatedServer3SingleResponseGroupSessionServerPortLauncher;
import replicatedsessionandserverport.rpc.duplex.singleresponse.example.AReplicatedSingleResponseReplicatedLatecomerSessionServer1Launcher;
import replicatedsessionandserverport.rpc.duplex.singleresponse.example.AReplicatedSingleResponseReplicatedLatecomerSessionServer2Launcher;
import replicatedsessionandserverport.rpc.duplex.singleresponse.example.AliceSingleResponseReplicatedSessionServerClientPortLauncher;
import replicatedsessionandserverport.rpc.duplex.singleresponse.example.BobSingleResponseReplicatedSessionServerClientPortLauncher;
import replicatedsessionandserverport.rpc.duplex.singleresponse.example.CathySingleResponseReplicatedSessionServerClientPortLauncher;

public class DemoerOfReplicatedServerAndSessionServer {
	public static void main(String args[]) {
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
//		ListenableVector<Class> classList = new AMainClassList();		
//		classList.add(AReplicatedSingleResponseReplicatedLatecomerSessionServer1Launcher.class);
//		classList.add(AReplicatedSingleResponseReplicatedLatecomerSessionServer2Launcher.class);
//		classList.add(AReplicatedServer1SingleResponseGroupSessionServerPortLauncher.class);
//		classList.add(AReplicatedServer2SingleResponseGroupSessionServerPortLauncher.class);
//		classList.add(AReplicatedServer3SingleResponseGroupSessionServerPortLauncher.class);
//		classList.add(AnAliceSingleResponseReplicatedSessionServerClientPortLauncher.class);
//		classList.add(ABobSingleResponseReplicatedSessionServerClientPortLauncher.class);
//		classList.add(ACathySingleResponseReplicatedSessionServerClientPortLauncher.class);


//		ObjectEditor.edit(classList);
	}

}
