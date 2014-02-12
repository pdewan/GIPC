package inputport.rpc.duplex.counter.example;


import port.APortDescription;
import port.AnAbstractPortLauncher;
import port.PortAccessKind;
import port.PortDescription;
import port.PortKind;
import port.PortLauncherSupport;
import port.PortMessageKind;
import inputport.InputPort;
import inputport.rpc.duplex.ADuplexRPCInputPortLauncherSupport;
import inputport.rpc.duplex.AnAbstractDuplexRPCServerPortLauncher;
import inputport.rpc.duplex.DuplexRPCInputPortSelector;
import inputport.rpc.duplex.DuplexRPCServerInputPort;
import examples.mvc.local.duplex.ACounter;

public class ADuplexCounterServerLauncher extends AnAbstractDuplexRPCServerPortLauncher implements DuplexCounterServerLauncher {
	public ADuplexCounterServerLauncher(String aServerName,
			String aServerPort) {
		super (aServerName, aServerPort);
	}
//	@Override
//	protected PortKind getPortKind() {
//		return PortKind.SERVER_INPUT_PORT;
//	}
//	protected PortDescription getPortDescription() {
//		return new APortDescription(PortKind.SERVER_INPUT_PORT, PortAccessKind.DUPLEX, PortMessageKind.RPC );
//	}
//	protected InputPort getPort() {
//		return DuplexRPCInputPortSelector.createDuplexRPCServerInputPort(
//				serverId, serverName);
//	}	
//	protected PortLauncherSupport getPortLauncherSupport() {
//		return new ADuplexRPCInputPortLauncherSupport();
//	}
//	protected void registerRemoteObjects() {
//		DuplexRPCServerInputPort aDuplexRPCServerInputPort = (DuplexRPCServerInputPort) mainPort;
//		aDuplexRPCServerInputPort.register(COUNTER1, new ACounter());
//		aDuplexRPCServerInputPort.register(COUNTER2, new ACounter());
//	}
	protected void registerRemoteObjects() {		
		register(COUNTER1, new ACounter());
		register(COUNTER2, new ACounter());
	}
	public static void main (String[] args) {
		(new ADuplexCounterServerLauncher(COUNTER_SERVER_NAME, COUNTER_SERVER_ID)).launch();
	}
}
