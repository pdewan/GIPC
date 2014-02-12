package port.old;

import port.sessionserver.ServerPortDescription;

public class AnOldGroupRPCSSPBobLauncher extends AnOlderGroupRPCStaticSessionPortLauncher {
	public static void main (String[] args) {
		ServerPortDescription[] others = {AliceDescription, CathyDescription};
		launchStaticSessionPartipant(others, BobDescription.getID(), BobDescription.getName());	
	}
}
