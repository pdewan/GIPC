package multiserverport.rpc.group.example;

import port.sessionserver.SessionParticipantDescription;

public class AliceGroupRPCMSPLauncher {
	
	public static void main (String[] args) {
		(new AGroupRPCMultiServerClientPortLauncher("Alice")).launch();
	}
}
