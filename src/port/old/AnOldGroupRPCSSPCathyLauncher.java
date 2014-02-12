package port.old;

import port.sessionserver.ServerPortDescription;

public class AnOldGroupRPCSSPCathyLauncher extends AnOlderGroupRPCStaticSessionPortLauncher {
	public static void main (String[] args) {
		ServerPortDescription[] others = {AliceDescription, BobDescription};
		launchStaticSessionPartipant(others, CathyDescription.getID(), CathyDescription.getName());	
	}
}
