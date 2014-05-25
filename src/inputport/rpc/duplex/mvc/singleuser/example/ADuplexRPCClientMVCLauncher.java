package inputport.rpc.duplex.mvc.singleuser.example;


import inputport.InputPort;
import inputport.rpc.duplex.DuplexRPCClientInputPort;
import inputport.rpc.simplex.mvc.example.ASimplexRPCClientMVCLauncher;
import inputport.rpc.simplex.mvc.example.SimplexRPCServerMVCLauncher;
import port.ParticipantChoice;
import port.PortAccessKind;
import port.PortLauncherSupport;
import port.SessionChoice;
import port.sessionserver.SessionParticipantDescription;
import examples.mvc.local.duplex.ACounter;
import examples.mvc.local.duplex.ADuplexFrostyAWTGUI;
import examples.mvc.local.duplex.ADuplexFrostyConsoleUI;
import examples.mvc.local.duplex.ADuplexFrostyModel;
import examples.mvc.local.duplex.ADuplexFrostyVerticalGUI;
import examples.mvc.local.duplex.Counter;
import examples.mvc.local.duplex.DuplexFrostyModel;
import examples.mvc.local.duplex.DuplexUpperCaser;
import examples.mvc.local.simplex.FrostyConsoleInteractor;





public class ADuplexRPCClientMVCLauncher extends ASimplexRPCClientMVCLauncher  {
	protected Counter counter;
	protected FrostyConsoleInteractor frostyConsoleInteractor;
	public ADuplexRPCClientMVCLauncher(String aClientName) {
		super(aClientName);
	}
	public ADuplexRPCClientMVCLauncher() {
	}
	public ADuplexRPCClientMVCLauncher(String aClientName, String aServerHost, String aServerId, String aServerName) {
		super(aClientName, aServerHost, aServerId, aServerName);		
	}
	public ADuplexRPCClientMVCLauncher(String aSessionServerHost, 
			String aServerId, String aServerName, String aMyId, String aMyName,
			String aSessionName,
			SessionChoice aSessionChoice, 
			boolean aShouldDelay,
			PortLauncherSupport aDelaysSupport,
			boolean aDoJitter,
			boolean aDoCausal, ParticipantChoice aChoice) {
		super(aSessionServerHost, aServerId, aServerName, aMyId, aMyName, aSessionName, aSessionChoice, aShouldDelay, aDelaysSupport, aDoJitter, aDoCausal, aChoice);

	}
	public ADuplexRPCClientMVCLauncher(SessionParticipantDescription[] aServerList, String aMyId, String aMyName,

			String aSessionName,
			SessionChoice aSessionChoice, 
			boolean aShouldDelay,
			PortLauncherSupport aDelaysSupport,
			boolean aDoJitter,
			boolean aDoCausal, 			
			ParticipantChoice aChoice) {
		super(aServerList, aMyId, aMyName, aSessionName, aSessionChoice, aShouldDelay, aDelaysSupport, aDoJitter, aDoCausal, aChoice);
	}
//	protected PortLauncherSupport getPortLauncherSupport() {
//		return new ADuplexRPCInputPortLauncherSupport();
//	}
//	protected InputPort getPort() {
//		return DuplexRPCInputPortSelector.createDuplexRPCClientInputPort(
//				serverHost, serverId, serverName, 	clientName);
//	}
	protected PortAccessKind getPortAccessKind() {
		return PortAccessKind.DUPLEX;
	}
	protected Class registeredUpperCaserClass() {
		return ADuplexRPCServerMVCLauncher.REGISTERED_DUPLEX_UPPER_CASER_CLASS;
	}
//	protected  void createProxies() {
//    	upperCaseProxy = (DuplexUpperCaser) DirectedRPCProxyGenerator.generateRPCProxy((DuplexRPCClientInputPort) mainPort, 
//				registeredUpperCaserClass());
//	}	
	protected void registerRemoteObjects() {
		DuplexRPCClientInputPort aDuplexRPCClientInputPort = (DuplexRPCClientInputPort) mainPort;
		counter = new ACounter();
		aDuplexRPCClientInputPort.register(counter);
	}
	protected void createUI(InputPort anInputPort) {
//		DuplexFrostyModel clientModel = getFrostyModel();	
//		(new ADuplexFrostyAWTGUI()).interact(clientModel);
//		(new ADuplexFrostyVerticalGUI()).interact(clientModel);
//		frostyConsoleInteractor =  new ADuplexFrostyConsoleUI();
//		frostyConsoleInteractor.interact(clientModel);
		createMVC(anInputPort);
		processConsoleInput();
//		frostyConsoleInteractor.processConsoleInput();
//		(new ADuplexFrostyConsoleUI()).interact(clientModel);			
	}
	protected void createMVC(InputPort anInputPort) {
		DuplexFrostyModel clientModel = getFrostyModel();	
		(new ADuplexFrostyAWTGUI()).interact(clientModel);
		(new ADuplexFrostyVerticalGUI()).interact(clientModel);
		frostyConsoleInteractor =  new ADuplexFrostyConsoleUI();
		frostyConsoleInteractor.interact(clientModel);
//		(new ADuplexFrostyConsoleUI()).interact(clientModel);
//		frostyConsoleInteractor.interact(clientModel);
	}
	protected void processConsoleInput() {
		frostyConsoleInteractor.processConsoleInput();
	}
	protected DuplexFrostyModel getFrostyModel() {
		return new  ADuplexFrostyModel((DuplexUpperCaser)upperCaseProxy, counter);	
	}	
	public static void main (String[] args) {		
		(new ADuplexRPCClientMVCLauncher(
				CLIENT_NAME, "localhost", 
				SimplexRPCServerMVCLauncher.MVC_SERVER_ID, 
				SimplexRPCServerMVCLauncher.MVC_SERVER_NAME )).launch();
	}
}
