package staticsessionport.rpc.group.mvc.example;

import port.ParticipantChoice;
import port.PortLauncherSupport;
import port.SessionChoice;
import port.sessionserver.SessionParticipantDescription;
import sessionport.rpc.group.mvc.flexible.example.AFlexibleSessionPortMVCClientLauncher;
import staticsessionport.datacomm.group.object.example.AGroupObjectStaticSessionPortLauncher;

public class AStaticSessionPortMVCClientLauncher extends AFlexibleSessionPortMVCClientLauncher{
//	SessionSendingCollaborativeFrostyModel frostyModel;
	public AStaticSessionPortMVCClientLauncher(SessionParticipantDescription[] aServerList, String aMyId, String aMyName,

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
}
