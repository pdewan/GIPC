package multiserverport.rpc.group.example;


import inputport.InputPort;
import inputport.rpc.group.GroupRPCInputPortSelector;
import inputport.rpc.group.GroupRPCServerInputPort;
import port.AnAbstractPortLauncher;
import port.sessionserver.relay.RelayerClientAndServerSupport;
import sessionport.rpc.duplex.relayed.example.Adder;
import sessionport.rpc.duplex.relayed.example.AnAdder;


public class AGroupRPCAdderServerLauncher extends AnAbstractPortLauncher{
	public AGroupRPCAdderServerLauncher(String aServerName, String aServerId) {
		super (aServerName, aServerId);
	}
	protected  InputPort getPort() {
		RelayerClientAndServerSupport.setRelayedCommunicaton(false);	

		return GroupRPCInputPortSelector.createGroupRPCServerInputPort(serverName, serverId);
		
	}
	protected void registerRemoteObjects() {
		Adder adder = new AnAdder();
		((GroupRPCServerInputPort) mainPort).register(Adder.class, adder);
		
	}
	
//	public static void launchGroupServer(String anId, String aName) {
//		Tracer.showInfo(true);
//		RelayerClientAndServerSupport.setRelayedCommunicaton(false);		
//		GroupRPCServerInputPort serverPort = GroupRPCInputPortSelector.createGroupRPCServerInputPort(anId, aName);
////		ConnectListener connectListener = new AGroupCallingConnectListener(serverPort);
////		serverPort.addConnectListener(connectListener);
//		Adder adder = new AnAdder();
//		serverPort.register(Adder.class, adder);
//		serverPort.connect();			
//	}
}
