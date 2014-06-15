package replicatedserverport.rpc.group.flexibleresponse.flexible;

import port.PortLauncherSupport;
import port.sessionserver.ASessionServerLauncher;
import port.sessionserver.ServerPortDescription;
import replicatedserverport.rpc.duplex.singleresponse.ASingleResponseReplicatedDuplexServerLauncherSupport;

public  class ASingleReponseReplicatedSessionServerLauncher extends ASessionServerLauncher{
//	public static String SESSION_SERVER_NAME = "Sessions Server";
//	public static String RELAYER_NAME = "Relayer";

	public  ASingleReponseReplicatedSessionServerLauncher (ServerPortDescription aServerPortDescription) {
		super(aServerPortDescription.getID(), aServerPortDescription.getName(), SESSION_SERVER_NAME);
	}
	protected PortLauncherSupport getReplicatedPortLauncherSupport() {
		return new ASingleResponseReplicatedDuplexServerLauncherSupport();
	}
	

	

}
