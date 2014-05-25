package sessionport.rpc.group.mvc.flexible.example;

import inputport.rpc.group.mvc.collaborative.example.AGroupRPCServerCollaborativeMVCLauncher;
import port.ParticipantChoice;
import port.PortKind;
import port.PortLauncherSupport;
import port.SessionChoice;
import port.sessionserver.SessionParticipantDescription;

public class AFlexibleSessionPortMVCServerLauncher extends AGroupRPCServerCollaborativeMVCLauncher {
	public AFlexibleSessionPortMVCServerLauncher(String aSessionServerHost, 
			String aSessionServerId, String aSessionServerName, String aMyId, String aMyName,
			String aSessionName,
			SessionChoice aSessionChoice, 
			boolean aShouldDelay,
			PortLauncherSupport aDelaysSupport,
			boolean aDoJitter,
			boolean aDoCausal, ParticipantChoice aChoice) {
		super(aSessionServerHost, aSessionServerId, aSessionServerName, aMyId, aMyName, aSessionName, aSessionChoice, aShouldDelay, aDelaysSupport, aDoJitter, aDoCausal, aChoice);

	}
	public AFlexibleSessionPortMVCServerLauncher(
			SessionParticipantDescription[] aServerList, 
			String aMyId, 
			String aMyName,
			String aSessionName,
			SessionChoice aSessionChoice, 
			boolean aShouldDelay,
			PortLauncherSupport aDelaysSupport,
			boolean aDoJitter,
			boolean aDoCausal, 			
			ParticipantChoice aChoice) {
		super(aServerList, aMyId, aMyName, aSessionName, aSessionChoice, aShouldDelay, aDelaysSupport, aDoJitter, aDoCausal, aChoice);
	}
	@Override
	protected PortKind getPortKind() {
		return PortKind.SESSION_PORT;
	}
}
