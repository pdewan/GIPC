package staticsessionport.rpc.group.mvc.example;

import port.ParticipantChoice;
import port.PortLauncherSupport;
import port.SessionChoice;
import port.sessionserver.SessionParticipantDescription;
import port.sessionserverAndRelay.mvc.example.UpperCaseSession;
import sessionport.rpc.group.mvc.direct.example.ServerDirectSessionPort;
import sessionport.rpc.group.mvc.flexible.example.AFlexibleSessionPortMVCServerLauncher;
import sessionport.rpc.group.mvc.flexible.example.SessionSendingCollaborativeFrostyModel;
import staticsessionport.datacomm.group.object.example.AGroupObjectStaticSessionPortLauncher;

public class AStaticSessionPortMVCServerLauncher extends AFlexibleSessionPortMVCServerLauncher implements ServerDirectSessionPort{
	public AStaticSessionPortMVCServerLauncher(SessionParticipantDescription[] aServerList, String aMyId, String aMyName,
			String aSessionName,
			SessionChoice aSessionChoice, 
			boolean aShouldDelay,
			PortLauncherSupport aDelaysSupport,
			boolean aDoJitter,
			boolean aDoCausal, 			
			ParticipantChoice aChoice) {
		super(aServerList, aMyId, aMyName, aSessionName, aSessionChoice, aShouldDelay, aDelaysSupport, aDoJitter, aDoCausal, aChoice);
	}
	protected void connectPorts() {
		AGroupObjectStaticSessionPortLauncher.waitForUserToOKConnectionThroughDialogBox();
		super.connectPorts();
	}
	public static void main (String[] args) {
		SessionParticipantDescription[] others = {
				ParticipantDescriptions.AliceDescription,
				ParticipantDescriptions.BobDescription};
		(new AStaticSessionPortMVCServerLauncher(others, 
				MVC_SERVER_ID, MVC_SERVER_NAME, 
				UpperCaseSession.SESSION_NAME, 
				SESSION_CHOICE,
				DO_DELAY, 
				DELAYS_SUPPORT, 
				DO_JITTER, 
				DO_CAUSAL, 
				PARTICIPANT_CHOICE)).launch();	
	}
}
