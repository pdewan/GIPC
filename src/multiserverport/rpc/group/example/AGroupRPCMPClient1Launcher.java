package multiserverport.rpc.group.example;

import port.old.AnOlderGroupRPCStaticSessionPortLauncher;
import port.sessionserver.ServerPortDescription;

public class AGroupRPCMPClient1Launcher extends AnOlderGroupRPCStaticSessionPortLauncher {
	public static void main (String[] args) {
		ServerPortDescription[] others = {BobDescription, CathyDescription};
		launchStaticSessionPartipant(others, null, AliceDescription.getName());	
	}
}
