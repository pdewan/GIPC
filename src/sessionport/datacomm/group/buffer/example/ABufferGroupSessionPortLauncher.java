package sessionport.datacomm.group.buffer.example;

import inputport.ConnectionListener;
import inputport.datacomm.group.GroupServerInputPort;

import java.nio.ByteBuffer;

import port.ParticipantChoice;
import port.sessionserver.SessionServerLauncher;
import sessionport.datacomm.duplex.buffer.ABufferDuplexReceiveListener;
import sessionport.datacomm.group.buffer.AGroupSendingConnectListener;
import sessionport.datacomm.group.buffer.BufferGroupSessionPortSelector;


public class ABufferGroupSessionPortLauncher {
	public static int SESSION_SERVER_PORT = 9090;
//	public static String SESSION_SERVER_NAME = "Sessions Server";
	public static void launchSessionPartipant( String anId, String aName) {
//		Tracer.showInfo(true);
		GroupServerInputPort<ByteBuffer> sessionPort = BufferGroupSessionPortSelector.createBufferGroupSessionPort("localhost", 
				"" + SESSION_SERVER_PORT, SessionServerLauncher.SESSION_SERVER_NAME, "Test Session", anId, aName, ParticipantChoice.MEMBER
				);
			
//		DuplexServerInputPort<ByteBuffer> sessionPort =	new ADuplexBufferSessionPortFullP2P("localhost", 
//				"" + ASessionsServer.SESSION_SERVER_PORT, ASessionsServer.SESSION_SERVER_NAME, "Test Session", anId, aName);
//		PrintingReplyingReceiveListener printingReplyingReceiveListener = new PrintingReplyingReceiveListener(sessionPort);		
		ConnectionListener connectListener = new AGroupSendingConnectListener(sessionPort);
//		sessionPort.addConnectListener(printingReplyingReceiveListener);
		sessionPort.addConnectionListener(connectListener);
		sessionPort.addReceiveListener(new ABufferDuplexReceiveListener());
//		serverInputPort.addDisconnectListener(echoingReceiveListener);
//		sessionPort.addReceiveListener(printingReplyingReceiveListener);	
		sessionPort.connect();		
	}

}
