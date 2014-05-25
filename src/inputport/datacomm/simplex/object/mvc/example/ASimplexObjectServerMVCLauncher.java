package inputport.datacomm.simplex.object.mvc.example;

import inputport.InputPort;
import inputport.datacomm.ReceiveListener;
import inputport.datacomm.simplex.object.ASimplexObjectInputPortLauncherSupport;
import inputport.datacomm.simplex.object.SimplexObjectInputPortSelector;
import inputport.rpc.simplex.mvc.example.ASimplexRPCServerMVCLauncher;
import port.PortLauncherSupport;
import examples.mvc.local.simplex.ASimplexUpperCaser;
import examples.mvc.local.simplex.SimplexUpperCaser;



public class ASimplexObjectServerMVCLauncher extends ASimplexRPCServerMVCLauncher   {
	
	protected SimplexUpperCaser upperCaser;
	
	public ASimplexObjectServerMVCLauncher(String aServerName,
			String aServerPort) {
		super (aServerName, aServerPort);
	}
	public ASimplexObjectServerMVCLauncher() {
	}
	protected PortLauncherSupport getPortLauncherSupport() {
		return new ASimplexObjectInputPortLauncherSupport();
	}

	protected InputPort getPort() {
		return SimplexObjectInputPortSelector.createSimplexServerInputPort(
				serverId, 
				serverName);
	}
	@Override
	protected ReceiveListener getReceiveListener (InputPort aServerInputPort) {
		return getObjectReceiveListener(aServerInputPort);
	}
	
	
	protected ReceiveListener getObjectReceiveListener (InputPort aServerInputPort) {
		return new ASimplexObjectServerUpperCaseReceiveTrapper(upperCaser);
	}

	@Override
	protected void registerRemoteObjects() {
		 upperCaser = new ASimplexUpperCaser();	

	}	
	public static void main (String[] args) {
		(new ASimplexObjectServerMVCLauncher()).launch();
	}
	



	
	
}
