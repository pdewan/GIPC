package staticsessionport.rpc.group.mvc.example;

import inputport.rpc.simplex.mvc.example.SimplexRPCServerMVCLauncher;
import port.ParticipantChoice;
import port.sessionserver.ASessionParticipantDescription;
import port.sessionserver.SessionParticipantDescription;
import sessionport.rpc.group.mvc.direct.example.ClientDirectSessionPort;
import sessionport.rpc.group.mvc.direct.example.ServerDirectSessionPort;
import sessionport.rpc.group.mvc.flexible.example.AliceSessionPort;
import sessionport.rpc.group.mvc.flexible.example.BobSessionPort;

public interface ParticipantDescriptions {
	static final SessionParticipantDescription AliceDescription =
			new ASessionParticipantDescription("localhost", 
				AliceSessionPort.ALICE_ID, 
				AliceSessionPort.ALICE_NAME, 
				ClientDirectSessionPort.PARTICIPANT_CHOICE, 
				null);
    static  SessionParticipantDescription BobDescription = new ASessionParticipantDescription("localhost",  BobSessionPort.BOB_ID, BobSessionPort.BOB_NAME, ClientDirectSessionPort.PARTICIPANT_CHOICE, null);
	static  SessionParticipantDescription MVCServerDescription = new ASessionParticipantDescription("localhost", SimplexRPCServerMVCLauncher.MVC_SERVER_ID, SimplexRPCServerMVCLauncher.MVC_SERVER_NAME,
			ServerDirectSessionPort.PARTICIPANT_CHOICE, null);

}
