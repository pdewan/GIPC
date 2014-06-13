package inputport.rpc.group.example;

import inputport.InputPort;
import inputport.rpc.duplex.example.ADuplexRPCServerInputPortLauncher;
import inputport.rpc.duplex.example.DuplexCounterAndSenderAwareSummer;
import inputport.rpc.group.AGroupRPCInputPortLauncherSupport;
import inputport.rpc.group.GroupRPCInputPortSelector;
import inputport.rpc.group.GroupRPCServerInputPort;
import port.PortLauncherSupport;



public class AGroupRPCServerInputPortLauncher extends ADuplexRPCServerInputPortLauncher  {
	
	public AGroupRPCServerInputPortLauncher(String aServerName,
			String aServerPort) {
		 super(aServerName, aServerPort);
	}
	public AGroupRPCServerInputPortLauncher() {
	}
	protected PortLauncherSupport getPortLauncherSupport() {
		return new AGroupRPCInputPortLauncherSupport();
	}
	protected InputPort getPort() {
		return GroupRPCInputPortSelector.createGroupRPCServerInputPort(serverId, serverName);
	}
	@Override
	protected void registerRemoteObjects()  {
		GroupRPCServerInputPort aGroupServerInputPort = (GroupRPCServerInputPort) mainPort;
		DuplexCounterAndSenderAwareSummer adder = new AGroupCounterAndSenderAwareSumPrinter(aGroupServerInputPort);
		aGroupServerInputPort.register(adder);
	}	
	public static void main (String[] args) {
		(new AGroupRPCServerInputPortLauncher()).launch();
	}
}
