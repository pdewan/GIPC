package staticsessionport.datacomm.group.object.example;

import port.sessionserver.SessionParticipantDescription;

public class AliceMemberGroupObjectSSPLauncher {
	
	public static void main (String[] args) {
		SessionParticipantDescription[] others = {AGroupObjectStaticSessionPortLauncher.BobDescription, AGroupObjectStaticSessionPortLauncher.CathyDescription};
		(new AGroupObjectStaticSessionPortLauncher(others, 
				AGroupObjectStaticSessionPortLauncher.AliceDescription.getID(), 
				AGroupObjectStaticSessionPortLauncher.AliceDescription.getName())).launch();
	}
}
