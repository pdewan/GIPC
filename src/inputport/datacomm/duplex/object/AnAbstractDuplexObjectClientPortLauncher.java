package inputport.datacomm.duplex.object;


import port.ATracingConnectionListener;
import port.AnAbstractPortLauncher;
import port.ParticipantChoice;
import port.PortAccessKind;
import port.PortKind;
import port.PortLauncherSupport;
import port.PortMessageKind;
import port.SessionChoice;
import port.sessionserver.SessionParticipantDescription;
import port.sessionserver.SessionServer;
import inputport.ConnectionListener;
import inputport.InputPort;
import inputport.rpc.DirectedRPCProxyGenerator;
import inputport.rpc.RPCProxyGenerator;
import inputport.rpc.duplex.ADuplexRPCInputPortLauncherSupport;
import inputport.rpc.duplex.DuplexRPCClientInputPort;
import inputport.rpc.duplex.DuplexRPCInputPortSelector;
import inputport.rpc.duplex.mvc.singleuser.example.ADuplexRPCServerMVCLauncher;
import inputport.rpc.simplex.SimplexRPCClientInputPort;
import inputport.rpc.simplex.mvc.example.ASimplexRPCClientMVCLauncher;
import inputport.rpc.simplex.mvc.example.ASimplexRPCServerMVCLauncher;
import inputport.rpc.simplex.mvc.example.SimplexRPCServerMVCLauncher;
import examples.mvc.local.duplex.ACounter;
import examples.mvc.local.duplex.ADuplexFrostyAWTGUI;
import examples.mvc.local.duplex.ADuplexFrostyConsoleUI;
import examples.mvc.local.duplex.ADuplexFrostyModel;
import examples.mvc.local.duplex.ADuplexFrostyVerticalGUI;
import examples.mvc.local.duplex.Counter;
import examples.mvc.local.duplex.DuplexFrostyModel;
import examples.mvc.local.duplex.DuplexUpperCaser;
import examples.mvc.local.simplex.ASimplexFrostyAWTGUI;
import examples.mvc.local.simplex.ASimplexFrostyConsoleUI;
import examples.mvc.local.simplex.ASimplexFrostyModel;
import examples.mvc.local.simplex.ASimplexFrostyVerticalGUI;
import examples.mvc.local.simplex.FrostyConsoleInteractor;
import examples.mvc.local.simplex.SimplexFrostyModel;
import examples.mvc.local.simplex.SimplexUpperCaser;
import examples.mvc.rmi.collaborative.relaying.AnEchoer;
import examples.mvc.rmi.collaborative.relaying.Echoer;





public class AnAbstractDuplexObjectClientPortLauncher extends AnAbstractPortLauncher   {
	
	public AnAbstractDuplexObjectClientPortLauncher(String aClientName) {
		super(aClientName);
	}
	public AnAbstractDuplexObjectClientPortLauncher() {
	}
	public AnAbstractDuplexObjectClientPortLauncher(String aClientName, String aServerHost, String aServerId, String aServerName) {
		super(aClientName, aServerHost, aServerId, aServerName);		
	}

	protected PortAccessKind getPortAccessKind() {
		return PortAccessKind.DUPLEX;
	}
	protected PortKind getPortKind() {
		return PortKind.CLIENT_INPUT_PORT;
	}
	protected PortMessageKind getPortMessageKind() {
		return PortMessageKind.OBJECT;
	}
	
	
}
