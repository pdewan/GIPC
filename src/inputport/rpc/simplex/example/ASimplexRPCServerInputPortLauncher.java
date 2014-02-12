package inputport.rpc.simplex.example;

import port.PortLauncherSupport;
import inputport.InputPort;
import inputport.datacomm.simplex.object.example.ASimplexObjectServerInputPortLauncher;
import inputport.rpc.simplex.ASimplexRPCInputPortLauncherSupport;
import inputport.rpc.simplex.SimplexRPCInputPortSelector;
import inputport.rpc.simplex.SimplexRPCServerInputPort;



public class ASimplexRPCServerInputPortLauncher extends ASimplexObjectServerInputPortLauncher   {	
	public ASimplexRPCServerInputPortLauncher(String aServerName,
			String aServerPort) {
		super (aServerName, aServerPort);
	}
	public ASimplexRPCServerInputPortLauncher() {
	}
	protected PortLauncherSupport getPortLauncherSupport() {
		return new ASimplexRPCInputPortLauncherSupport();
	}
	protected InputPort getPort() {
		return SimplexRPCInputPortSelector.createSimplexRPCServerInputPort(
				serverId, 
				serverName);
	}
	@Override
	protected void registerRemoteObjects() {
		SimplexRPCServerInputPort aSimplexRPCServerInputPort = (SimplexRPCServerInputPort) mainPort;
		UpperCasePrinter adder = new ASenderAwareUpperCasePrinter(aSimplexRPCServerInputPort);
		aSimplexRPCServerInputPort.register(UpperCasePrinter.class, adder);
	}	
	public static void main (String[] args) {
		(new ASimplexRPCServerInputPortLauncher()).launch();
	}	
}
