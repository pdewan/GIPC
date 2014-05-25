package replicatedserverport.rpc.group.moreflexibleresponse.flexibe.jitter.example;

import inputport.ConnectionListener;
import inputport.InputPort;
import port.ATracingConnectionListener;
import port.ParticipantChoice;
import port.PortLauncherSupport;
import port.SessionChoice;
import port.sessionserver.SessionParticipantDescription;
import replicatedserverport.rpc.duplex.preferredresponse.APreferredResponseReplicatedPortLauncherSupport;
import replicatedserverport.rpc.group.flexibleresponse.flexibejitter.example.AJitteryFlexibleResponseReplicatedSessionPortLauncher;
import replicatedserverport.rpc.group.flexibleresponse.flexible.example.ReplicationChoice;

public class AJitteryMoreFlexibleResponseReplicatedSessionPortLauncher  extends 
                  AJitteryFlexibleResponseReplicatedSessionPortLauncher{
	public AJitteryMoreFlexibleResponseReplicatedSessionPortLauncher(
			String aSessionServerHost, String aServerId, String aServerName,
			String aMyId, String aMyName, SessionChoice aSessionChoice,
			boolean aShouldDelay, PortLauncherSupport aDelaysSupport,
			boolean aDoCausal, ReplicationChoice aReplicationChoice,
			ParticipantChoice aParticipantChoice, SessionParticipantDescription[] aServersDescription, boolean aDoJitter) {
		super(aSessionServerHost, aServerId, aServerName, aMyId, aMyName,
				aSessionChoice, aShouldDelay, aDelaysSupport, aDoCausal,
				aReplicationChoice, null, aServersDescription, aDoJitter);
	}
	protected PortLauncherSupport getReplicatedPortLauncherSupport() {
		if (replicationChoice == ReplicationChoice.PREFERRED_RESPONSE)
			return new APreferredResponseReplicatedPortLauncherSupport(myName);
		else
			return super.getReplicatedPortLauncherSupport();
	}
	@Override
	protected ConnectionListener getConnectionListener(InputPort anInputPort) {
		return new ATracingConnectionListener(anInputPort);
	}
	

}
