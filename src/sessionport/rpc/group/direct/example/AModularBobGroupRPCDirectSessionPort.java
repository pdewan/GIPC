package sessionport.rpc.group.direct.example;

import port.ParticipantChoice;
import port.sessionserver.SessionServerLauncher;
import sessionport.datacomm.group.object.flexible.AFlexibleSessionPortClientLauncher;
import sessionport.rpc.group.mvc.flexible.example.BobSessionPort;

public class AModularBobGroupRPCDirectSessionPort implements BobSessionPort {
	public static void main(String[] args) {

		(new AModularGroupRPCDirectSessionPortLauncher(
				"localhost",
				SessionServerLauncher.SESSION_SERVER_ID,
				SessionServerLauncher.SESSION_SERVER_NAME, 
				BOB_ID, BOB_NAME, 
				"Test Session", 
				AFlexibleSessionPortClientLauncher.SESSION_CHOICE,
				AFlexibleSessionPortClientLauncher.DO_DELAY, 
				null, 
				false, 
				AFlexibleSessionPortClientLauncher.DO_CAUSAL, 
				ParticipantChoice.MEMBER)).launch();
	}


}
