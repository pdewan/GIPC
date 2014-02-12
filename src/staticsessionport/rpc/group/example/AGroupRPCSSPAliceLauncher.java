package staticsessionport.rpc.group.example;

import port.sessionserver.SessionParticipantDescription;

public class AGroupRPCSSPAliceLauncher {
	
	public static void main (String[] args) {
		SessionParticipantDescription[] others = {AGroupRPCStaticSessionPortLauncher.BobDescription, AGroupRPCStaticSessionPortLauncher.CathyDescription};
		(new AGroupRPCStaticSessionPortLauncher(others, 
				AGroupRPCStaticSessionPortLauncher.AliceDescription.getID(), 
				AGroupRPCStaticSessionPortLauncher.AliceDescription.getName())).launch();
	}
}
