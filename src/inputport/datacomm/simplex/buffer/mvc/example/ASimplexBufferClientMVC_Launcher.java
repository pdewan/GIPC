package inputport.datacomm.simplex.buffer.mvc.example;


import inputport.InputPort;
import inputport.datacomm.simplex.SimplexClientInputPort;
import inputport.datacomm.simplex.buffer.ASimplexBufferInputPortLauncherSupport;
import inputport.datacomm.simplex.buffer.SimplexBufferInputPortSelector;
import inputport.datacomm.simplex.object.mvc.example.ASimplexObjectClientMVCLauncher;
import inputport.datacomm.simplex.object.mvc.example.ASimplexUpperCaserProxy;

import java.nio.ByteBuffer;

import port.PortLauncherSupport;

import examples.mvc.local.duplex.ADuplexFrostyConsoleUI;
import examples.mvc.local.simplex.ASimplexFrostyAWTGUI;
import examples.mvc.local.simplex.ASimplexFrostyConsoleUI;
import examples.mvc.local.simplex.ASimplexFrostyModel;
import examples.mvc.local.simplex.ASimplexFrostyVerticalGUI;
import examples.mvc.local.simplex.FrostyConsoleInteractor;
import examples.mvc.local.simplex.SimplexFrostyModel;
import examples.mvc.local.simplex.SimplexUpperCaser;





public class ASimplexBufferClientMVC_Launcher extends ASimplexObjectClientMVCLauncher  {
	protected SimplexUpperCaser upperCaseProxy;

	public ASimplexBufferClientMVC_Launcher(String aClientName) {
		super(aClientName);
	}
	public ASimplexBufferClientMVC_Launcher() {
	}
	protected PortLauncherSupport getPortLauncherSupport() {
		return new ASimplexBufferInputPortLauncherSupport();
	}

	@Override
	protected InputPort getPort() {
		return SimplexBufferInputPortSelector.createSimplexClientInputPort(
				serverHost, serverId, serverName, 	clientName);
	}


	@Override
	protected  void createProxies() {

    	upperCaseProxy = new ASimplexUpperCaserProxy(
    			new ASimplexBufferClientUpperCaseSendTrapper((SimplexClientInputPort<ByteBuffer>) mainPort));


	}
	
	@Override 
	protected void createUI(InputPort anInputPort) {
		SimplexFrostyModel clientModel = new ASimplexFrostyModel(upperCaseProxy);	
		(new ASimplexFrostyAWTGUI()).interact(clientModel);
		(new ASimplexFrostyVerticalGUI()).interact(clientModel);
		FrostyConsoleInteractor frostyInteractor =  new ASimplexFrostyConsoleUI();
		frostyInteractor.interact(clientModel);
		frostyInteractor.processConsoleInput();
//		(new ASimplexFrostyConsoleUI()).interact(clientModel);	
		
		
	}
	
	public static void main (String[] args) {		
		(new ASimplexBufferClientMVC_Launcher()).launch();
	}
	


}
