package replicatedserverport.rpc.group.flexibleresponse.flexible.example;

import port.PortLauncherSupport;
import port.sessionserver.ServerPortDescription;
import port.sessionserver.relay.late.ALatecomerSessionServerLauncher;
import replicatedserverport.rpc.groupserver.singleresponse.ASingleResponseReplicatedGroupServerLauncherSupport;

public  class ASingleReponseReplicatedLatecomerSessionServerLauncher extends ALatecomerSessionServerLauncher{
//	public static String SESSION_SERVER_NAME = "Sessions Server";
//	public static String RELAYER_NAME = "Relayer";

	public  ASingleReponseReplicatedLatecomerSessionServerLauncher (ServerPortDescription aServerPortDescription) {
		super(aServerPortDescription.getID(), aServerPortDescription.getName(), SESSION_SERVER_NAME);
	}
	protected PortLauncherSupport getReplicatedPortLauncherSupport() {
		return new ASingleResponseReplicatedGroupServerLauncherSupport();
	}
	
	@Override
	protected void doPostConnectsAsyncOperations() {
//		System.out.println("Ready to Serve");
	}
	

}
