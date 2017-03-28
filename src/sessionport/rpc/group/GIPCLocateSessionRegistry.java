package sessionport.rpc.group;

import port.ParticipantChoice;
import port.PortLauncher;
import port.SessionChoice;
import port.sessionserver.SessionServerLauncher;
import sessionport.datacomm.group.object.flexible.AFlexibleSessionPortClientLauncher;
import sessionport.rpc.group.direct.example.AModularGroupRPCDirectSessionPortLauncher;
import inputport.rpc.GIPCLocateRegistry;
import inputport.rpc.GIPCRegistry;
import inputport.rpc.duplex.AnAbstractDuplexRPCServerPortLauncher;

public class GIPCLocateSessionRegistry  {
	static final String DEFAULT_SESSION_NAME = "GIPC Session";
	
	public static GIPCSessionRegistry createSessionRegistry(
			String aSessionName,
			String aSessionServerHost, 
			int aMemberPortNumber, 
			String aMemberName, 
			SessionChoice aSessionChoice,
			Integer aNumMembersToWaitFor) {

		GroupRPCSessionPortLauncher result =
				new AGroupRPCSessionPortLauncher(
						"localhost",
						SessionServerLauncher.SESSION_SERVER_ID,
						SessionServerLauncher.SESSION_SERVER_NAME, 
						"" + aMemberPortNumber
						, aMemberName, 
						"Test Session", 
						AFlexibleSessionPortClientLauncher.SESSION_CHOICE,
						AFlexibleSessionPortClientLauncher.DO_DELAY, 
						null, 
						false, 
						AFlexibleSessionPortClientLauncher.DO_CAUSAL, 
						ParticipantChoice.MEMBER,
						aNumMembersToWaitFor);
		result.launch();
		result.waitForConnections();
		return result;
	}

}
