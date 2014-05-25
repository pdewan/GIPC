package inputport.datacomm.simplex.object.example;

import inputport.InputPort;
import inputport.datacomm.simplex.SimplexClientInputPort;
import inputport.datacomm.simplex.buffer.example.ASimplexBufferClientInputPortLauncher;
import inputport.datacomm.simplex.buffer.example.UserInterfaceManager;
import inputport.datacomm.simplex.object.ASimplexObjectInputPortLauncherSupport;
import inputport.datacomm.simplex.object.SimplexObjectInputPortSelector;
import port.PortLauncherSupport;




public class ASimplexObjectClientInputPortLauncher extends ASimplexBufferClientInputPortLauncher  {

	public ASimplexObjectClientInputPortLauncher(String aClientName) {
		super(aClientName);
	}

	protected PortLauncherSupport getPortLauncherSupport() {
		return new ASimplexObjectInputPortLauncherSupport();
	}
	
	@Override
	protected InputPort getPort() {
		return SimplexObjectInputPortSelector.createSimplexClientInputPort(
				SERVER_HOST, SERVER_ID, SERVER_NAME, 	clientName);
	}
	protected UserInterfaceManager createUserInterfaceManager(InputPort anInputPort) {
		return new ASimplexObjectFrostyConsoleUI((SimplexClientInputPort<Object>) anInputPort);
	}



}
