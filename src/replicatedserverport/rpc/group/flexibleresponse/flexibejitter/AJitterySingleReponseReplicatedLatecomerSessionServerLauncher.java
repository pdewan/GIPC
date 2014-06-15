package replicatedserverport.rpc.group.flexibleresponse.flexibejitter;

import port.PortLauncherSupport;
import port.jitter.AJitterControllingPortLauncherSupport;
import port.sessionserver.ServerPortDescription;
import replicatedserverport.rpc.group.flexibleresponse.flexible.example.ASingleReponseReplicatedLatecomerSessionServerLauncher;

public  class AJitterySingleReponseReplicatedLatecomerSessionServerLauncher extends ASingleReponseReplicatedLatecomerSessionServerLauncher{
//	public static String SESSION_SERVER_NAME = "Sessions Server";
//	public static String RELAYER_NAME = "Relayer";

	
	public AJitterySingleReponseReplicatedLatecomerSessionServerLauncher(
			ServerPortDescription aServerPortDescription) {
		super(aServerPortDescription);
		// TODO Auto-generated constructor stub
	}

	protected PortLauncherSupport getJitterPortLauncherSupport() {	
			return new AJitterControllingPortLauncherSupport();
	}	

}
