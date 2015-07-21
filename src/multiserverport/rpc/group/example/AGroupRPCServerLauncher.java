package multiserverport.rpc.group.example;


import inputport.rpc.group.GroupRPCInputPortSelector;
import inputport.rpc.group.GroupRPCServerInputPort;
import port.sessionserver.relay.RelayerClientAndServerSupport;
import sessionport.rpc.duplex.relayed.example.Adder;
import sessionport.rpc.duplex.relayed.example.AnAdder;
import util.trace.Tracer;


public class AGroupRPCServerLauncher {
	public static void launchGroupServer(String anId, String aName) {
		Tracer.showInfo(true);
		RelayerClientAndServerSupport.setRelayedCommunicaton(false);		
		GroupRPCServerInputPort serverPort = GroupRPCInputPortSelector.createGroupRPCServerInputPort(anId, aName);
//		ConnectListener connectListener = new AGroupCallingConnectListener(serverPort);
//		serverPort.addConnectListener(connectListener);
		Adder adder = new AnAdder();
		serverPort.register(Adder.class, adder);
		serverPort.connect();			
	}
}
