package staticsessionport.datacomm.group.object.example;

import port.old.AnOldGroupRPCStaticSessionPortLauncher;
import port.sessionserver.SessionParticipantDescription;

public class AGroupObjectSSPBobLauncher extends AnOldGroupRPCStaticSessionPortLauncher {
	public static void main (String[] args) {
		SessionParticipantDescription[] others = {AGroupObjectStaticSessionPortLauncher.AliceDescription, AGroupObjectStaticSessionPortLauncher.CathyDescription};
		(new AGroupObjectStaticSessionPortLauncher(others, 
				AGroupObjectStaticSessionPortLauncher.BobDescription.getID(), 
				AGroupObjectStaticSessionPortLauncher.BobDescription.getName())).launch();
//		SessionParticipantDescription[] others = {AliceDescription, CathyDescription};
//		launchStaticSessionPartipant(others, BobDescription.getID(), BobDescription.getName());	
	}
}
