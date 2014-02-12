package port.demos;

import replicatedsessionandserverport.rpc.duplex.singleresponse.example.ABobSingleResponseReplicatedSessionServerClientPortLauncher;
import replicatedsessionandserverport.rpc.duplex.singleresponse.example.ACathySingleResponseReplicatedSessionServerClientPortLauncher;
import replicatedsessionandserverport.rpc.duplex.singleresponse.example.AReplicatedServer1SingleResponseGroupSessionServerPortLauncher;
import replicatedsessionandserverport.rpc.duplex.singleresponse.example.AReplicatedServer2SingleResponseGroupSessionServerPortLauncher;
import replicatedsessionandserverport.rpc.duplex.singleresponse.example.AReplicatedServer3SingleResponseGroupSessionServerPortLauncher;
import replicatedsessionandserverport.rpc.duplex.singleresponse.example.AReplicatedSingleResponseReplicatedLatecomerSessionServer1Launcher;
import replicatedsessionandserverport.rpc.duplex.singleresponse.example.AReplicatedSingleResponseReplicatedLatecomerSessionServer2Launcher;
import replicatedsessionandserverport.rpc.duplex.singleresponse.example.AnAliceSingleResponseReplicatedSessionServerClientPortLauncher;
import bus.uigen.models.MainClassLaunchingUtility;

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
				AnAliceSingleResponseReplicatedSessionServerClientPortLauncher.class,
				ABobSingleResponseReplicatedSessionServerClientPortLauncher.class,
				ACathySingleResponseReplicatedSessionServerClientPortLauncher.class
		};
		MainClassLaunchingUtility.interactiveLaunch(classes);
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
