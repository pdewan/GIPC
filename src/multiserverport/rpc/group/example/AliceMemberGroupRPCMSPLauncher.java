package multiserverport.rpc.group.example;

import port.sessionserver.SessionParticipantDescription;

public class AliceMemberGroupRPCMSPLauncher {
	
	public static void main (String[] args) {
		(new AGroupRPCMultiServerClientPortLauncher("Alice")).launch();
	}
}
