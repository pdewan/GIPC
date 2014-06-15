package replicatedserverport.rpc.group.fixedresponse.direct.example;

import port.PortLauncherSupport;
import port.sessionserver.ASessionServerLauncher;
import port.sessionserver.ServerPortDescription;
import replicatedserverport.rpc.duplex.fixedresponse.AFixedResponseReplicatedDuplexServerLauncherSupport;

public  class AFixedResponseReplicatedSessionServerLauncher extends ASessionServerLauncher{
//	public static String SESSION_SERVER_NAME = "Sessions Server";
//	public static String RELAYER_NAME = "Relayer";

	public  AFixedResponseReplicatedSessionServerLauncher (ServerPortDescription aServerPortDescription) {
		super(aServerPortDescription.getID(), aServerPortDescription.getName(), SESSION_SERVER_NAME);
	}
	protected PortLauncherSupport getReplicatedPortLauncherSupport() {
		return new AFixedResponseReplicatedDuplexServerLauncherSupport();
	}
	

	

}
