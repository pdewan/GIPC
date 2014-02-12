package inputport.datacomm.duplex.object.echoer.example;

import port.ATracingConnectionListener;
import port.AnAbstractPortLauncher;
import port.ParticipantChoice;
import port.PortAccessKind;
import port.PortKind;
import port.PortLauncherSupport;
import port.PortMessageKind;
import port.SessionChoice;
import port.sessionserver.SessionParticipantDescription;
import inputport.ConnectionListener;
import inputport.InputPort;
import inputport.rpc.duplex.DuplexRPCInputPort;
import inputport.rpc.duplex.DuplexRPCServerInputPort;
import inputport.rpc.duplex.ReplyRPCProxyGenerator;
import inputport.rpc.duplex.mvc.singleuser.example.ADuplexRPCClientMVCLauncher;
import inputport.rpc.duplex.mvc.singleuser.example.ADuplexRPCServerMVCLauncher;
import inputport.rpc.duplex.mvc.singleuser.example.DuplexRPCClientMVCLauncher;
import inputport.rpc.simplex.SimplexRPCServerInputPort;
import examples.mvc.local.duplex.ADuplexUpperCaser;
import examples.mvc.local.duplex.Counter;
import examples.mvc.local.duplex.DuplexUpperCaser;
import examples.mvc.local.simplex.ASimplexUpperCaser;
import examples.mvc.local.simplex.SimplexUpperCaser;



public abstract class AnAbstractDuplexObjectServerPortLauncher extends AnAbstractPortLauncher   {

	public AnAbstractDuplexObjectServerPortLauncher(String aServerName,
			String aServerPort) {
		super (aServerName, aServerPort);
	}	
	@Override
	protected PortAccessKind getPortAccessKind() {
		return PortAccessKind.DUPLEX;
	}	
	public AnAbstractDuplexObjectServerPortLauncher() {
	}	
	@Override
	protected PortKind getPortKind() {
		return PortKind.SERVER_INPUT_PORT;
	}
	@Override
	protected PortMessageKind getPortMessageKind() {
		return PortMessageKind.OBJECT;
	}
	

	
}
