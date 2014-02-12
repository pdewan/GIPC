package staticsessionport.rpc.group.example;

import port.old.AnOldGroupRPCStaticSessionPortLauncher;
import port.sessionserver.SessionParticipantDescription;

public class AGroupRPCSSPCathyLauncher extends AnOldGroupRPCStaticSessionPortLauncher {
	public static void main (String[] args) {
		SessionParticipantDescription[] others = {AGroupRPCStaticSessionPortLauncher.AliceDescription, AGroupRPCStaticSessionPortLauncher.BobDescription};
		(new AGroupRPCStaticSessionPortLauncher(others, 
				AGroupRPCStaticSessionPortLauncher.CathyDescription.getID(), 
				AGroupRPCStaticSessionPortLauncher.CathyDescription.getName())).launch();
//		SessionParticipantDescription[] others = {AliceDescription, BobDescription};
//		launchStaticSessionPartipant(others, CathyDescription.getID(), CathyDescription.getName());	
	}
}
