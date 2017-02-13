package inputport.rpc.simplex.mvc.example;


import inputport.ConnectionListener;
import inputport.InputPort;
import inputport.rpc.DirectedRPCProxyGenerator;
import inputport.rpc.simplex.SimplexRPCClientInputPort;
import port.ATracingConnectionListener;
import port.AnAbstractPortLauncher;
import port.ParticipantChoice;
import port.PortAccessKind;
import port.PortLauncherSupport;
import port.SessionChoice;
import port.sessionserver.SessionParticipantDescription;
import examples.mvc.local.simplex.ASimplexFrostyAWTGUI;
import examples.mvc.local.simplex.ASimplexFrostyConsoleUI;
import examples.mvc.local.simplex.ASimplexFrostyModel;
import examples.mvc.local.simplex.ASimplexFrostyVerticalGUI;
import examples.mvc.local.simplex.FrostyConsoleInteractor;
import examples.mvc.local.simplex.SimplexFrostyModel;
import examples.mvc.local.simplex.SimplexUpperCaser;





public class ASimplexRPCClientMVCLauncher extends AnAbstractPortLauncher  implements SimplexRPCClientMVCLauncher{
	public ASimplexRPCClientMVCLauncher() {
		clientName = CLIENT_NAME;
		serverName = SimplexRPCServerMVCLauncher.MVC_SERVER_NAME;
	}
	public ASimplexRPCClientMVCLauncher(String aClientName) {
		super(aClientName);
		serverName = SimplexRPCServerMVCLauncher.MVC_SERVER_NAME;
	}
	public ASimplexRPCClientMVCLauncher(String aClientName, String aServerHost, String aServerId, String aServerName) {
		super(aClientName, aServerHost, aServerId, aServerName);		
	}
	public ASimplexRPCClientMVCLauncher(String aSessionServerHost, 
			String aServerId, String aServerName, String aMyId, String aMyName,
			String aSessionName,
			SessionChoice aSessionChoice, 
			boolean aShouldDelay,
			PortLauncherSupport aDelaysSupport,
			boolean aDoJitter,
			boolean aDoCausal, ParticipantChoice aChoice) {
		super(aSessionServerHost, aServerId, aServerName, aMyId, aMyName, aSessionName, aSessionChoice, aShouldDelay, aDelaysSupport, aDoJitter, aDoCausal, aChoice);
	}
	public ASimplexRPCClientMVCLauncher(SessionParticipantDescription[] aServerList, String aMyId, String aMyName,

			String aSessionName,
			SessionChoice aSessionChoice, 
			boolean aShouldDelay,
			PortLauncherSupport aDelaysSupport,
			boolean aDoJitter,
			boolean aDoCausal, 			
			ParticipantChoice aChoice) {
		super(aServerList, aMyId, aMyName, aSessionName, aSessionChoice, aShouldDelay, aDelaysSupport, aDoJitter, aDoCausal, aChoice);
	}
	protected SimplexUpperCaser upperCaseProxy;
//	protected PortLauncherSupport getPortLauncherSupport() {
//		return new ASimplexRPCInputPortLauncherSupport();
//	}
	protected  ConnectionListener getConnectionListener (InputPort anInputPort) {
		return new ATracingConnectionListener(anInputPort);
	}
	public PortAccessKind getPortAccessKind() {
		return PortAccessKind.SIMPLEX;
	}
//	protected PortKind getPortKind() {
//		return PortKind.CLIENT_INPUT_PORT;
//	}
//	protected InputPort getPort() {
//		return SimplexRPCInputPortSelector.createSimplexRPCClientInputPort(
//				serverHost, serverId, serverName, clientName);
//	}
	protected Class registeredUpperCaserClass() {
		return ASimplexRPCServerMVCLauncher.REGISTERED_UPPER_CASER_CLASS;
	}
	protected  void createProxies() {
//		RPCProxyGenerator rpcProxyGenerator = ((SimplexRPCClientInputPort) mainPort).getRPCProxyGenerator();
//    	upperCaseProxy = (SimplexUpperCaser) rpcProxyGenerator.generateRPCProxy(registeredUpperCaserClass());
    	upperCaseProxy = (SimplexUpperCaser) DirectedRPCProxyGenerator.generateRPCProxy((SimplexRPCClientInputPort) mainPort, registeredUpperCaserClass());

	}	 
	protected void createUI(InputPort anInputPort) {
		SimplexFrostyModel clientModel = getFrostyModel();
		(new ASimplexFrostyAWTGUI()).interact(clientModel);
		(new ASimplexFrostyVerticalGUI()).interact(clientModel);
//		(new ASimplexFrostyConsoleUI()).interact(clientModel);	
		FrostyConsoleInteractor frostyInteractor =  new ASimplexFrostyConsoleUI();
		frostyInteractor.interact(clientModel);
		frostyInteractor.processConsoleInput();
	}
	protected SimplexFrostyModel getFrostyModel() {
		return new ASimplexFrostyModel(upperCaseProxy);
	}	
	public static void main (String[] args) {		
		(new ASimplexRPCClientMVCLauncher(CLIENT_NAME, "localhost", SimplexRPCServerMVCLauncher.MVC_SERVER_ID, SimplexRPCServerMVCLauncher.MVC_SERVER_NAME )).launch();
	}
}
