package replicatedserverport.rpc.group.fixedresponse.direct.example;

import port.ParticipantChoice;
import port.PortLauncherSupport;
import port.SessionChoice;
import port.sessionserver.SessionParticipantDescription;
import replicatedserverport.rpc.duplex.fixedresponse.AFixedResponseReplicatedPortLauncherSupport;
import replicatedserverport.rpc.group.flexibleresponse.flexible.example.AFlexibleResponseReplicatedSessionPortLauncher;
import replicatedserverport.rpc.group.flexibleresponse.flexible.example.ReplicationChoice;

public class AMoreFlexibleResponseReplicatedSessionPortLauncher 
	extends AFlexibleResponseReplicatedSessionPortLauncher{

	
    

//	public static final SessionParticipantDescription[] SERVERS_DESCRIPTION = {
//		AliceServerLauncher.SERVER_1_DESCRIPTION,
//		BobServerLauncher.SERVER_2_DESCRIPTION,
//		CathyServerLauncher.SERVER_3_DESCRIPTION};
	protected static SessionParticipantDescription[] serversDescription;
	
	

//	String id, name;
//	boolean addConnectListener, addReplyingReceiveListener, greetOnReadingInput;
	public AMoreFlexibleResponseReplicatedSessionPortLauncher(String aSessionServerHost, 
			String aServerId, String aServerName, String aMyId, String aMyName, 
			SessionChoice aSessionChoice, 
			boolean aShouldDelay,
			PortLauncherSupport aDelaysSupport,
			boolean aDoCausal,
			ReplicationChoice aReplicationChoice,
			SessionParticipantDescription[] aServersDescription) {
		super(aSessionServerHost, aServerId, aServerName, aMyId, aMyName, aSessionChoice, aShouldDelay, aDelaysSupport, aDoCausal, aReplicationChoice, ParticipantChoice.MEMBER, aServersDescription);
	
	}

	
	protected PortLauncherSupport getReplicatedPortLauncherSupport() {
		if (replicationChoice == ReplicationChoice.LOCAL_RESPONSE)
			return new AFixedResponseReplicatedPortLauncherSupport();
		else
			return super.getReplicatedPortLauncherSupport();
		
		
	}
	


}
