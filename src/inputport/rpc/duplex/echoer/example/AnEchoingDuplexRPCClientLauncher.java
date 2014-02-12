package inputport.rpc.duplex.echoer.example;


import port.ATracingConnectionListener;
import port.AnAbstractPortLauncher;
import port.ParticipantChoice;
import port.PortAccessKind;
import port.PortLauncherSupport;
import port.SessionChoice;
import port.sessionserver.SessionParticipantDescription;
import port.sessionserver.SessionServer;
import inputport.ConnectionListener;
import inputport.InputPort;
import inputport.rpc.DirectedRPCProxyGenerator;
import inputport.rpc.RPCProxyGenerator;
import inputport.rpc.duplex.ADuplexRPCInputPortLauncherSupport;
import inputport.rpc.duplex.AnAbstractDuplexRPCClientPortLauncher;
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





public class AnEchoingDuplexRPCClientLauncher extends AnAbstractDuplexRPCClientPortLauncher implements EchoingRPCClientLauncher  {	
	public AnEchoingDuplexRPCClientLauncher(String aClientName, String aServerHost, String aServerId, String aServerName) {
		super(aClientName, aServerHost, aServerId, aServerName);		
	}
	protected  ConnectionListener getConnectionListener (InputPort anInputPort) {
		return new ATracingConnectionListener(anInputPort);
	}		
	protected Class registeredUpperCaserClass() {
		return ADuplexRPCServerMVCLauncher.REGISTERED_DUPLEX_UPPER_CASER_CLASS;
	}
	protected SimplexUpperCaser upperCaseProxy;
	
	protected  void createProxies() {
    	upperCaseProxy = (SimplexUpperCaser) createProxy(registeredUpperCaserClass());
    			
	}
	protected Counter counter;
	protected void registerRemoteObjects() {
		DuplexRPCClientInputPort aDuplexRPCClientInputPort = (DuplexRPCClientInputPort) mainPort;
		counter = new ACounter();
		aDuplexRPCClientInputPort.register(counter);
	}
	protected void createUI(InputPort anInputPort) {
		createMVC(anInputPort);
		processConsoleInput();			
	}
	protected FrostyConsoleInteractor frostyConsoleInteractor;
	protected void createMVC(InputPort anInputPort) {
		DuplexFrostyModel clientModel = getFrostyModel();
		frostyConsoleInteractor =  new ADuplexFrostyConsoleUI();
		frostyConsoleInteractor.interact(clientModel);
	}	
	protected DuplexFrostyModel getFrostyModel() {
		return new  ADuplexFrostyModel((DuplexUpperCaser)upperCaseProxy, counter);	
	}	
	protected void processConsoleInput() {
		frostyConsoleInteractor.processConsoleInput();
	}

				
	public static void main (String[] args) {		
		(new AnEchoingDuplexRPCClientLauncher(
				CLIENT_NAME, "localhost", 
				EchoingDuplexServerLauncher.ECHOER_SERVER_ID, 
				EchoingDuplexServerLauncher.ECHOER_SERVER_NAME )).launch();
	}
}
