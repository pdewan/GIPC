package multiserverport.rpc.group.example;

import inputport.ConnectionListener;
import inputport.rpc.group.GroupRPCServerInputPort;
import sessionport.rpc.group.GroupRPCSessionPort;

import java.util.Scanner;

import port.ParticipantChoice;
import port.old.Adder;
import port.old.AnAdder;
import port.sessionserver.ASessionParticipantDescription;
import port.sessionserver.SessionParticipantDescription;
import port.sessionserver.relay.RelayerClientAndServerSupport;
import sessionport.rpc.group.AGroupCallingConnectListener;
import staticsessionport.rpc.group.GroupRPCStaticSessionPortSelector;
import util.trace.Tracer;


public class AGroupRPCClientLauncher {
	protected static SessionParticipantDescription server1Description = 
		new ASessionParticipantDescription("localhost", AGroupRPCServer1Launcher.server1Id, AGroupRPCServer1Launcher.server1Name, null);
	protected static SessionParticipantDescription server2Description = 
		new ASessionParticipantDescription("localhost", AGroupRPCServer2Launcher.server2Id, AGroupRPCServer2Launcher.server2Name, null);
	protected static SessionParticipantDescription[] servers = {server1Description, server2Description};
	protected static final String REMOTE_END_POINT = "Echo Servers" ; 

	public static void launchClient(String aName) {
		Tracer.showInfo(true);
		RelayerClientAndServerSupport.setRelayedCommunicaton(false);
		GroupRPCServerInputPort sessionPort = GroupRPCStaticSessionPortSelector.createGroupRPCStaticSessionPort(servers, REMOTE_END_POINT, aName, "Add Servers", ParticipantChoice.SYMMETRIC_JOIN);					
		ConnectionListener connectListener = new AGroupCallingConnectListener(sessionPort);
		sessionPort.addConnectionListener(connectListener);
		Adder adder = new AnAdder();
		sessionPort.register(Adder.class, adder);
		Scanner in = new Scanner(System.in);
		String message  = in.nextLine();
		Tracer.info("About to connect to peers");
		sessionPort.connect();	
		 in.nextLine();

	}
}
