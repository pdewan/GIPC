package staticsessionport.rpc.group.mvc.example;

import inputport.InputPort;
import inputport.datacomm.ReceiveListener;
import inputport.rpc.DirectedRPCProxyGenerator;
import inputport.rpc.simplex.SimplexRPCClientInputPort;
import inputport.rpc.simplex.mvc.example.SimplexRPCServerMVCLauncher;
import port.ParticipantChoice;
import port.PortAccessKind;
import port.PortKind;
import port.PortLauncherSupport;
import port.SessionChoice;
import port.sessionserver.SessionParticipantDescription;
import sessionport.rpc.group.GroupRPCSessionPort;
import sessionport.rpc.group.mvc.flexible.example.AFlexibleSessionPortMVCClientLauncher;
import sessionport.rpc.group.mvc.flexible.example.ASessionSendingCollaborativeFrostyModel;
import sessionport.rpc.group.mvc.flexible.example.SessionSendingCollaborativeFrostyModel;
import staticsessionport.datacomm.group.object.example.AGroupObjectStaticSessionPortLauncher;
import examples.mvc.local.duplex.DuplexFrostyModel;
import examples.mvc.local.duplex.DuplexUpperCaser;
import examples.mvc.local.simplex.SimplexUpperCaser;

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
