package sessionport.datacomm.group.object.flexible.jitter.example;

import port.ParticipantChoice;
import port.PortLauncherSupport;
import port.SessionChoice;
import port.jitter.AJitterControllingPortLauncherSupport;
import sessionport.datacomm.group.object.flexible.AFlexibleSessionPortClientLauncher;

public class AJitteryFlexibleSessionPortClientLauncher 
	extends AFlexibleSessionPortClientLauncher{
	public static boolean DO_JITTER = false;

	protected boolean doJitter;
	public AJitteryFlexibleSessionPortClientLauncher(String aSessionServerHost, 
			String aServerId, String aServerName, String aMyId, String aMyName, 
			SessionChoice aSessionChoice, 
			boolean aShouldDelay,
			PortLauncherSupport aDelaysSupport,
			boolean aDoCausal,
			ParticipantChoice aParticipantChoice, boolean aDoJitter) {
		super(aSessionServerHost, aServerId, aServerName, aMyId, aMyName, aSessionChoice, aShouldDelay, aDelaysSupport, aDoCausal, aParticipantChoice);
		doJitter = aDoJitter;
	}

	protected PortLauncherSupport getJitterPortLauncherSupport() {
		if (doJitter)
			return new AJitterControllingPortLauncherSupport();
		return null;
	}

}
