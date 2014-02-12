package inputport.rpc.duplex.example;

import port.PortLauncherSupport;
import inputport.ConnectionListener;
import inputport.InputPort;
import inputport.datacomm.duplex.DuplexServerInputPort;
import inputport.datacomm.duplex.object.echoer.example.AFrostyObjectConnectionListener;
import inputport.rpc.duplex.ADuplexRPCInputPortLauncherSupport;
import inputport.rpc.duplex.DuplexRPCInputPortSelector;
import inputport.rpc.duplex.DuplexRPCServerInputPort;
import inputport.rpc.simplex.example.ASimplexRPCServerInputPortLauncher;



public class ADuplexRPCServerInputPortLauncher extends ASimplexRPCServerInputPortLauncher  {
	
	public ADuplexRPCServerInputPortLauncher(String aServerName,
			String aServerPort) {
		super (aServerName, aServerPort);
		
	}
	public ADuplexRPCServerInputPortLauncher() {
		
	}
	protected PortLauncherSupport getPortLauncherSupport() {
		return new ADuplexRPCInputPortLauncherSupport();
	}
	protected InputPort getPort() {
		return DuplexRPCInputPortSelector.createDuplexRPCServerInputPort(
			serverId, 
			serverName);
	}
	@Override
	protected void registerRemoteObjects()  {
		DuplexRPCServerInputPort aDuplexRPCServerInputPort = (DuplexRPCServerInputPort) mainPort;
		
		DuplexUpperCasePrinter upperCasePrinter = 
				new ADuplexUpperCasePrinter((DuplexRPCServerInputPort) aDuplexRPCServerInputPort);
		
		aDuplexRPCServerInputPort.register(upperCasePrinter);
	}
	@Override
	protected ConnectionListener getConnectionListener (InputPort aServerInputPort) {
		return new AFrostyObjectConnectionListener((DuplexServerInputPort)aServerInputPort);
	}
	
	public static void main (String[] args) {
		(new ADuplexRPCServerInputPortLauncher()).launch();
	}
//	public static void main (String[] args) {
//		setTracing();
//		DuplexRPCServerInputPort aServerInputPort = 
//			DuplexRPCInputPortSelector.createDuplexRPCServerInputPort(
//				SERVER_PORT, 
//				SERVER_NAME);
//		ADuplexObjectServerInputPortLauncher.addListeners(aServerInputPort);
//		registerMethods(aServerInputPort);
//		aServerInputPort.connect();		
//	}	

	
//	public static void registerMethods(DuplexRPCServerInputPort aServerInputPort) {
//		DuplexCounterAndSenderAwareSumComputerAndPrinter adder = new ADuplexCounterAndSenderAwareSumPrinter(aServerInputPort);
//		aServerInputPort.register(DuplexCounterAndSenderAwareSumComputerAndPrinter.class, adder);
//	}	
}
