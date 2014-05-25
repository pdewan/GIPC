package staticsessionport.rpc.group.mvc.example;

import port.sessionserver.SessionParticipantDescription;
import port.sessionserverAndRelay.mvc.example.UpperCaseSession;
import sessionport.rpc.group.mvc.direct.example.ClientDirectSessionPort;
import sessionport.rpc.group.mvc.flexible.example.AliceSessionPort;

public class AnAliceMVCStaticSessionPortLauncher implements AliceSessionPort,ClientDirectSessionPort {
	public static void main (String[] args) {
		SessionParticipantDescription[] others = {
				ParticipantDescriptions.MVCServerDescription,
				ParticipantDescriptions.BobDescription};
		(new AStaticSessionPortMVCClientLauncher(others, 
				ALICE_ID, ALICE_NAME, 
				UpperCaseSession.SESSION_NAME, 
				SESSION_CHOICE,
				DO_DELAY, 
				DELAYS_SUPPORT, 
				DO_JITTER, 
				DO_CAUSAL, 
				PARTICIPANT_CHOICE)).launch();
	}
}
