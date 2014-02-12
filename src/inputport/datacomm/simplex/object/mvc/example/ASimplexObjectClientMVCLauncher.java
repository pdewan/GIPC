package inputport.datacomm.simplex.object.mvc.example;


import port.PortLauncherSupport;
import inputport.InputPort;
import inputport.datacomm.simplex.SimplexClientInputPort;
import inputport.datacomm.simplex.object.ASimplexObjectInputPortLauncherSupport;
import inputport.datacomm.simplex.object.SimplexObjectInputPortSelector;
import inputport.rpc.simplex.mvc.example.ASimplexRPCClientMVCLauncher;
import examples.mvc.local.simplex.ASimplexFrostyAWTGUI;
import examples.mvc.local.simplex.ASimplexFrostyConsoleUI;
import examples.mvc.local.simplex.ASimplexFrostyModel;
import examples.mvc.local.simplex.ASimplexFrostyVerticalGUI;
import examples.mvc.local.simplex.SimplexFrostyModel;
import examples.mvc.local.simplex.SimplexUpperCaser;





public class ASimplexObjectClientMVCLauncher extends ASimplexRPCClientMVCLauncher  {
//	protected SimplexUpperCaser upperCaseProxy;

	public ASimplexObjectClientMVCLauncher(String aClientName) {
		super(aClientName);
	}
	public ASimplexObjectClientMVCLauncher() {
		super();
	}
	protected PortLauncherSupport getPortLauncherSupport() {
		return new ASimplexObjectInputPortLauncherSupport();
	}

	@Override
	protected InputPort getPort() {
		return SimplexObjectInputPortSelector.createSimplexClientInputPort(
				serverHost, serverId, serverName, 	clientName);
	}


	@Override
	protected  void createProxies() {

    	upperCaseProxy = new ASimplexUpperCaserProxy((SimplexClientInputPort) mainPort);


	}
	
//	@Override 
//	protected void createUI(InputPort anInputPort) {
//		SimplexFrostyModel clientModel = new ASimplexFrostyModel(upperCaseProxy);	
//		(new ASimplexFrostyAWTGUI(clientModel)).manageUserInterface();
//		(new ASimplexFrostyVerticalGUI(clientModel)).manageUserInterface();
//		(new ASimplexFrostyConsoleUI(clientModel)).manageUserInterface();	
//		
//		
//	}
//	
	public static void main (String[] args) {		
		(new ASimplexObjectClientMVCLauncher()).launch();
	}
	


}
