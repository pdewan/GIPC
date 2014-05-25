package sessionport.datacomm.group.object.example;


import inputport.ConnectionListener;
import port.ParticipantChoice;
import port.sessionserver.relay.RelayerClientAndServerSupport;
import sessionport.datacomm.duplex.object.AnObjectDuplexReceiveListener;
import sessionport.datacomm.group.GroupSessionPort;
import sessionport.datacomm.group.object.ObjectGroupSessionPortSelector;


public class AnObjectGroupSessionPortLauncher {
	public static int SESSION_SERVER_PORT = 9090;
	public static String SESSION_SERVER_NAME = "Session Server";

	public static void launchSessionPartipant( String anId, String aName, ParticipantChoice aChoice) {
//		Tracer.showInfo(true);
		RelayerClientAndServerSupport.setRelayedCommunicaton(false);
		GroupSessionPort<Object> sessionPort = ObjectGroupSessionPortSelector.createObjectGroupSessionPort("localhost", 
				"" + SESSION_SERVER_PORT, SESSION_SERVER_NAME, "Test Session", anId, aName, aChoice
				);
			
//		DuplexServerInputPort<ByteBuffer> sessionPort =	new ADuplexBufferSessionPortFullP2P("localhost", 
//				"" + ASessionsServer.SESSION_SERVER_PORT, ASessionsServer.SESSION_SERVER_NAME, "Test Session", anId, aName);
//		PrintingReplyingReceiveListener printingReplyingReceiveListener = new PrintingReplyingReceiveListener(sessionPort);		
		ConnectionListener connectListener = new AnObjectGroupSendingConnectListener(sessionPort);
//		sessionPort.addConnectListener(printingReplyingReceiveListener);
		sessionPort.addConnectionListener(connectListener);
		sessionPort.addReceiveListener(new AnObjectDuplexReceiveListener());
//		serverInputPort.addDisconnectListener(echoingReceiveListener);
//		sessionPort.addReceiveListener(printingReplyingReceiveListener);	
		sessionPort.connect();		
	}

}
