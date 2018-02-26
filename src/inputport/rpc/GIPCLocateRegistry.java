package inputport.rpc;

import inputport.rpc.duplex.AnAbstractDuplexRPCClientPortLauncher;
import inputport.rpc.duplex.AnAbstractDuplexRPCServerPortLauncher;

public class GIPCLocateRegistry {
	static protected int lastServerId = 0;
	static protected int lastClientId = 0;
	static protected String serverNamePrefix = "Server";
	static protected String clientNamePrefix = "Client";
	static protected String lastServerName;
	static protected String lastClientName;
	public static GIPCRegistry createRegistry(int aPortNumber) {
		lastServerName = serverNamePrefix + lastServerId;
		lastServerId++;
		String aServerId = "" + aPortNumber;
		GIPCRegistry result = new AnAbstractDuplexRPCServerPortLauncher(lastServerName, aServerId);	
		result.launch();
//		GIPCRegistry result = createRegistryWithoutConnecting(aPortNumber);
//		result.launch();
		return result;
	}
	public static GIPCRegistry createRegistryWithoutConnecting(int aPortNumber) {
		lastServerName = serverNamePrefix + lastServerId;
		lastServerId++;
		String aServerId = "" + aPortNumber;
		AnAbstractDuplexRPCServerPortLauncher result = new AnAbstractDuplexRPCServerPortLauncher(lastServerName, aServerId);	
		result.launchWithoutConnecting();
//		result.launch();
		return result;
	}
	public static GIPCRegistry getRegistry(String aHostName, int aPortNumber, String aClientName) {
//		lastClientName = clientNamePrefix + lastClientId;
//		lastClientId++;
//		String aServerId = "" + aPortNumber;
//		AnAbstractDuplexRPCClientPortLauncher result = new AnAbstractDuplexRPCClientPortLauncher(
//				aClientName, 
//				aHostName,
//				aServerId,
//				lastServerName);
		GIPCRegistry result = getRegistryWithoutConnecting(aHostName, aPortNumber, aClientName);
		boolean aStatus = result.connect();
		if (aStatus)
		return result;
		else
			return null;
	}
	public static GIPCRegistry getRegistryWithoutConnecting(String aHostName, int aPortNumber, String aClientName) {
//		lastClientName = clientNamePrefix + lastClientId;
//		lastClientId++;
		String aServerId = "" + aPortNumber;
		AnAbstractDuplexRPCClientPortLauncher result = new AnAbstractDuplexRPCClientPortLauncher(
				aClientName, 
				aHostName,
				aServerId,
				lastServerName);	
		result.launchWithoutConnecting();
//		result.waitForConnections();
		return result;
	}
	

}
