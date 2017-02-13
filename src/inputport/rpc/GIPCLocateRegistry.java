package inputport.rpc;

import inputport.rpc.duplex.AnAbstractDuplexRPCClientPortLauncher;
import inputport.rpc.duplex.AnAbstractDuplexRPCServerPortLauncher;

public class GIPCLocateRegistry {
	static int lastServerId = 0;
	static int lastClientId = 0;
	static String serverNamePrefix = "Server";
	static String clientNamePrefix = "Client";
	static String lastServerName;
	static String lastClientName;
	public static GIPCRegistry createRegistry(int aPortNumber) {
		lastServerName = serverNamePrefix + lastServerId;
		lastServerId++;
		String aServerId = "" + aPortNumber;
		GIPCRegistry result = new AnAbstractDuplexRPCServerPortLauncher(lastServerName, aServerId);	
		result.launch();
		return result;
	}
	public static GIPCRegistry getRegistry(String aHostName, int aPortNumber, String aClientName) {
//		lastClientName = clientNamePrefix + lastClientId;
//		lastClientId++;
		String aServerId = "" + aPortNumber;
		AnAbstractDuplexRPCClientPortLauncher result = new AnAbstractDuplexRPCClientPortLauncher(
				aClientName, 
				aHostName,
				aServerId,
				lastServerName);	
		result.launch();
		result.waitForConnections();
		return result;
	}
	

}
