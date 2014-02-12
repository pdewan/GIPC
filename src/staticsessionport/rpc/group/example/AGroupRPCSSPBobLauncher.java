package staticsessionport.rpc.group.example;

import port.old.AnOldGroupRPCStaticSessionPortLauncher;
import port.sessionserver.SessionParticipantDescription;

public class AGroupRPCSSPBobLauncher extends AnOldGroupRPCStaticSessionPortLauncher {
	public static void main (String[] args) {
		SessionParticipantDescription[] others = {AGroupRPCStaticSessionPortLauncher.AliceDescription, AGroupRPCStaticSessionPortLauncher.CathyDescription};
		(new AGroupRPCStaticSessionPortLauncher(others, 
				AGroupRPCStaticSessionPortLauncher.BobDescription.getID(), 
				AGroupRPCStaticSessionPortLauncher.BobDescription.getName())).launch();
//		SessionParticipantDescription[] others = {AliceDescription, CathyDescription};
//		launchStaticSessionPartipant(others, BobDescription.getID(), BobDescription.getName());	
	}
}
