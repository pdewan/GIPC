package replicatedserverport.rpc.group.flexibleresponse.flexible.example;

import port.sessionserver.ASessionServerLauncher;
import port.sessionserver.ServerPortDescription;

public  class AnEarliestReponseReplicatedSessionServerLauncher extends ASessionServerLauncher{
//	public static String SESSION_SERVER_NAME = "Sessions Server";
//	public static String RELAYER_NAME = "Relayer";

	public  AnEarliestReponseReplicatedSessionServerLauncher (ServerPortDescription aServerPortDescription) {
		super(aServerPortDescription.getID(), aServerPortDescription.getName(), SESSION_SERVER_NAME);
	}
	
	
//	public PortLauncherSupport createPortLauncherSupport() {
//		return new ALatecomerRelayerAndSessionServerLauncherSupport();
////		return new AReplicatedServerSingleResponsePortLauncherSupport();
//
//	}
	

}
