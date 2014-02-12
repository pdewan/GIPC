package replicatedserverport.rpc.group.flexibleresponse.flexibejitter.example;

import port.ParticipantChoice;
import port.PortLauncherSupport;
import port.SessionChoice;
import port.jitter.AJitterControllingPortLauncherSupport;
import port.sessionserver.SessionParticipantDescription;
import replicatedserverport.rpc.group.flexibleresponse.flexible.example.AFlexibleResponseReplicatedSessionPortLauncher;
import replicatedserverport.rpc.group.flexibleresponse.flexible.example.ReplicationChoice;

public class AJitteryFlexibleResponseReplicatedSessionPortLauncher  extends 
                 AFlexibleResponseReplicatedSessionPortLauncher{
	public static boolean DO_JITTER = false;
	boolean doJitter;
	public AJitteryFlexibleResponseReplicatedSessionPortLauncher(
			String aSessionServerHost, String aServerId, String aServerName,
			String aMyId, String aMyName, SessionChoice aSessionChoice,
			boolean aShouldDelay, PortLauncherSupport aDelaysSupport,
			boolean aDoCausal, ReplicationChoice aReplicationChoice,
			ParticipantChoice aParticipantChoice,
			SessionParticipantDescription[] aServersDescription, boolean aDoJitter) {
		super(aSessionServerHost, aServerId, aServerName, aMyId, aMyName,
				aSessionChoice, aShouldDelay, aDelaysSupport, aDoCausal,
				aReplicationChoice, 
				aParticipantChoice,
				aServersDescription);
		doJitter = aDoJitter;
	}
	protected PortLauncherSupport getJitterPortLauncherSupport() {
		if (doJitter)
			return new AJitterControllingPortLauncherSupport();
		return null;
	}

}
