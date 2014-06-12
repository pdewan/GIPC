package multiserverport.rpc.group.example;

import port.sessionserver.SessionParticipantDescription;

public class BobMemberGroupRPCMSPLauncher {
	
	public static void main (String[] args) {
		(new AGroupRPCMultiServerClientPortLauncher("Bob")).launch();
	}
}
