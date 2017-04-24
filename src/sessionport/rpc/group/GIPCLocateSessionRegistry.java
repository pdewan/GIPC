package sessionport.rpc.group;

import java.util.HashMap;
import java.util.Map;

import port.AnAbstractPortLauncher;
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
	static Map<String, AGroupRPCSessionPortLauncher> sessionNameToRegistry = new HashMap();
	
	public static GIPCSessionRegistry createOrGetSessionRegistry(
			String aSessionName,
			String aSessionServerHost, 
			int aMemberPortNumber, 
			String aMemberName, 
			SessionChoice aSessionChoice,
			short aNumMembersToWaitFor) {
//		GIPCSessionRegistry result = createSessionRegistryWithoutConnecting(aSessionName, aSessionServerHost, aMemberPortNumber, aMemberName, aSessionChoice, aNumMembersToWaitFor);
//		result.connect();
		AGroupRPCSessionPortLauncher result = sessionNameToRegistry.get(aSessionName);
		if (result != null) {
			 return result;
		}

//		GroupRPCSessionPortLauncher result =
			result =	new AGroupRPCSessionPortLauncher(
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
		sessionNameToRegistry.put(aSessionName, result);
		result.launch();
		result.waitForConnections();
		return result;
	}
	public static GIPCSessionRegistry createSessionRegistryWithoutConnecting(
			String aSessionName,
			String aSessionServerHost, 
			int aMemberPortNumber, 
			String aMemberName, 
			SessionChoice aSessionChoice,
			short aNumMembersToWaitFor) {
		AGroupRPCSessionPortLauncher result = sessionNameToRegistry.get(aSessionName);
		if (result != null) {
			 return result;
		}

//		AGroupRPCSessionPortLauncher 
		result =
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
		result.launchWithoutConnecting();
		sessionNameToRegistry.put(aSessionName, result);

//		result.connect();
//		result.launch();
//		result.waitForConnections();
		return result;
	}

}
