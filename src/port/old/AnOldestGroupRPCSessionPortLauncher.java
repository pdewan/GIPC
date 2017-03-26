package port.old;


import inputport.ConnectionListener;
import port.ParticipantChoice;
import port.sessionserver.relay.RelayerClientAndServerSupport;
import sessionport.rpc.duplex.relayed.example.Adder;
import sessionport.rpc.duplex.relayed.example.AnAdder;
import sessionport.rpc.group.AnOldGroupCallingConnectListener;
import sessionport.rpc.group.GroupRPCSessionPort;
import sessionport.rpc.group.GroupRPCSessionPortSelector;
import util.trace.Tracer;


public class AnOldestGroupRPCSessionPortLauncher {
	public static int SESSION_SERVER_PORT = 9090;
	public static String SESSION_SERVER_NAME = "Session Server";
	public static void launchSessionPartipant( String anId, String aName) {
		Tracer.showInfo(true);
		RelayerClientAndServerSupport.setRelayedCommunicaton(false);
		GroupRPCSessionPort sessionPort = GroupRPCSessionPortSelector.createGroupRPCSessionPort("localhost", 
				"" + SESSION_SERVER_PORT, SESSION_SERVER_NAME, "Test Session", anId, aName, ParticipantChoice.MEMBER
				);	
		ConnectionListener connectListener = new AnOldGroupCallingConnectListener(sessionPort);
		sessionPort.addConnectionListener(connectListener);
		Adder adder = new AnAdder();
		sessionPort.register(Adder.class, adder);
		sessionPort.connect();		
	}
	

}
