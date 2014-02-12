package sessionport.rpc.group.mvc.flexible.example;

import java.net.InetAddress;

import inputport.ConnectionListener;
import inputport.InputPort;
import inputport.datacomm.ReceiveListener;
import inputport.rpc.group.mvc.collaborative.example.AGroupRPCServerCollaborativeMVCLauncher;
import port.ATracingConnectionListener;
import port.ParticipantChoice;
import port.PortKind;
import port.PortLauncherSupport;
import port.SessionChoice;
import port.causal.ACausalGroupSessionPortLauncherSupport;
import port.delay.AClientDelayingPortLauncherSupport;
import port.delay.example.AnEchoingObjectReceiveListener;
import port.sessionserver.AServerPortDescription;
import port.sessionserver.SessionParticipantDescription;
import port.sessionserver.SessionServerLauncher;
import port.sessionserver.example.APrintingSessionObserver;
import port.sessionserver.relay.mvc.example.ASessionMemberMVCServerLauncher;
import port.sessionserverAndRelay.mvc.example.UpperCaseSession;

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
