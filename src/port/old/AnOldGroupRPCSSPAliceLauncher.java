package port.old;

import port.sessionserver.ServerPortDescription;

public class AnOldGroupRPCSSPAliceLauncher extends AnOlderGroupRPCStaticSessionPortLauncher {
	public static void main (String[] args) {
		ServerPortDescription[] others = {BobDescription, CathyDescription};
		launchStaticSessionPartipant(others, AliceDescription.getID(), AliceDescription.getName());	
	}
}
