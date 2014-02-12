package staticsessionport.datacomm.group.object.example;

import port.old.AnOldGroupRPCStaticSessionPortLauncher;
import port.sessionserver.SessionParticipantDescription;

public class AGroupObjectSSPCathyLauncher extends AnOldGroupRPCStaticSessionPortLauncher {
	public static void main (String[] args) {
		SessionParticipantDescription[] others = {AGroupObjectStaticSessionPortLauncher.AliceDescription, AGroupObjectStaticSessionPortLauncher.BobDescription};
		(new AGroupObjectStaticSessionPortLauncher(others, 
				AGroupObjectStaticSessionPortLauncher.CathyDescription.getID(), 
				AGroupObjectStaticSessionPortLauncher.CathyDescription.getName())).launch();
//		SessionParticipantDescription[] others = {AliceDescription, BobDescription};
//		launchStaticSessionPartipant(others, CathyDescription.getID(), CathyDescription.getName());	
	}
}
