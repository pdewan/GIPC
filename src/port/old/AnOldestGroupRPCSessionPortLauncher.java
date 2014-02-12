package port.old;


import port.ParticipantChoice;
import port.sessionserver.relay.RelayerClientAndServerSupport;
import inputport.ConnectionListener;
import sessionport.rpc.group.AGroupCallingConnectListener;
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
		ConnectionListener connectListener = new AGroupCallingConnectListener(sessionPort);
		sessionPort.addConnectionListener(connectListener);
		Adder adder = new AnAdder();
		sessionPort.register(Adder.class, adder);
		sessionPort.connect();		
	}
	

}
