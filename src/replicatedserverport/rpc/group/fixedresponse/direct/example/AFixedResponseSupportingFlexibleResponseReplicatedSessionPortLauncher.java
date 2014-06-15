package replicatedserverport.rpc.group.fixedresponse.direct.example;

import port.ParticipantChoice;
import port.PortLauncherSupport;
import port.SessionChoice;
import port.sessionserver.SessionParticipantDescription;
import replicatedserverport.rpc.duplex.fixedresponse.AFixedResponseReplicatedPortLauncherSupport;
import replicatedserverport.rpc.group.flexibleresponse.flexible.AFlexibleResponseReplicatedSessionPortLauncher;
import replicatedserverport.rpc.group.flexibleresponse.flexible.ReplicationChoice;
// combine with flexible response, no?
public class AFixedResponseSupportingFlexibleResponseReplicatedSessionPortLauncher 
	extends AFlexibleResponseReplicatedSessionPortLauncher{

	
    


	protected static SessionParticipantDescription[] serversDescription;
	
	


	public AFixedResponseSupportingFlexibleResponseReplicatedSessionPortLauncher(String aSessionServerHost, 
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
