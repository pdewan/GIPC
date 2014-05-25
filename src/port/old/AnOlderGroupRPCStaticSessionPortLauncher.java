package port.old;

import inputport.ConnectionListener;

import java.util.Scanner;

import port.sessionserver.AServerPortDescription;
import port.sessionserver.ServerPortDescription;
import port.sessionserver.relay.RelayerClientAndServerSupport;
import sessionport.rpc.group.AGroupCallingConnectListener;
import sessionport.rpc.group.GroupRPCSessionPort;
import util.trace.Tracer;


public class AnOlderGroupRPCStaticSessionPortLauncher {
	protected static ServerPortDescription AliceDescription = new AServerPortDescription("localhost", "9100", "Alice");
	protected static ServerPortDescription BobDescription = new AServerPortDescription("localhost", "9101", "Bob");
	protected static ServerPortDescription CathyDescription = new AServerPortDescription("localhost", "9102", "Cathy");
	protected static final String REMOTE_END_POINT = "Echo Servers" ; 


	public static void launchStaticSessionPartipant(ServerPortDescription[] aServerList, String anId, String aName) {
		Tracer.showInfo(true);
		RelayerClientAndServerSupport.setRelayedCommunicaton(false);
		GroupRPCSessionPort sessionPort = null;
		// fix this later if needed
//				GroupRPCStaticSessionPortSelector.createGroupRPCStaticSessionPort(aServerList, anId, aName, REMOTE_END_POINT, null
//				);	
		ConnectionListener connectListener = new AGroupCallingConnectListener(sessionPort);
		sessionPort.addConnectionListener(connectListener);
		Adder adder = new AnAdder();
		sessionPort.register(Adder.class, adder);
		Scanner in = new Scanner(System.in);
		System.out.println("Press any key to connect to peers");
		String message  = in.nextLine();
		Tracer.info("About to connect to peers");
		sessionPort.connect();		
	}
}
