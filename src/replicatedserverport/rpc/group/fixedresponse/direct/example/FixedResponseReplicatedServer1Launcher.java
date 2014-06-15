package replicatedserverport.rpc.group.fixedresponse.direct.example;

import inputport.datacomm.simplex.object.example.AliceClientLauncher;
import replicatedserverport.rpc.duplex.fixedresponse.ClientServerMapping;
import replicatedserverport.rpc.group.flexibleresponse.flexible.Server1Launcher;

public class FixedResponseReplicatedServer1Launcher implements Server1Launcher{

	
	public static void main (String args[]) {
		ClientServerMapping.setServer(AliceClientLauncher.ALICE, Server1Launcher.SERVER1);

		(new AFixedResponseReplicatedSessionServerLauncher(SERVER_1_DESCRIPTION)).launch();

	}
	

	

}
