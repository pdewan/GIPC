package replicatedserverport.rpc.group.fixedresponse.direct.example;

import inputport.datacomm.simplex.object.example.CathyClientLauncher;
import replicatedserverport.rpc.duplex.fixedresponse.ClientServerMapping;
import replicatedserverport.rpc.group.flexibleresponse.flexible.example.Server3Launcher;

public class FixedResponseReplicatedServer3Launcher implements Server3Launcher{
	
	
	public static void main (String args[]) {
		ClientServerMapping.setServer(CathyClientLauncher.CATHY, Server3Launcher.SERVER3);

		(new AFixedResponseReplicatedSessionServerLauncher(SERVER_3_DESCRIPTION)).launch();

	}
	

	

}
