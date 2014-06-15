package replicatedserverport.rpc.group.fixedresponse.direct.example;

import inputport.datacomm.simplex.object.example.BobClientLauncher;
import replicatedserverport.rpc.duplex.fixedresponse.ClientServerMapping;
import replicatedserverport.rpc.group.flexibleresponse.flexible.example.Server2Launcher;

public class FixedResponseReplicatedServer2Launcher implements Server2Launcher{
	
	
	public static void main (String args[]) {
		ClientServerMapping.setServer(BobClientLauncher.BOB, Server2Launcher.SERVER2);

		(new AFixedResponseReplicatedSessionServerLauncher(SERVER_2_DESCRIPTION)).launch();

	}
	

	

}
