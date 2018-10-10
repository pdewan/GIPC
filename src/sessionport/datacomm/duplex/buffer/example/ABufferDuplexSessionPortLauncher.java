package sessionport.datacomm.duplex.buffer.example;

import java.nio.ByteBuffer;

import inputport.ConnectionListener;
import port.ParticipantChoice;
import sessionport.datacomm.duplex.DuplexSessionPort;
import sessionport.datacomm.duplex.buffer.ABufferDuplexReceiveListener;
import sessionport.datacomm.duplex.buffer.ASendingConnectListener;
import sessionport.datacomm.duplex.buffer.BufferDuplexSessionPortSelector;


public class ABufferDuplexSessionPortLauncher {
	public static int SESSION_SERVER_PORT = 9090;
	public static String SESSION_SERVER_NAME = "Session Server";

	public static void launchSessionPartipant( String anId, String aName, ParticipantChoice aJoinChoice) {
//		Tracer.showInfo(true);
		DuplexSessionPort<ByteBuffer> sessionPort = BufferDuplexSessionPortSelector.createBufferDuplexSessionPort("localhost", 
				"" + SESSION_SERVER_PORT, SESSION_SERVER_NAME, "Test Session", anId, aName, ParticipantChoice.MEMBER
				);
			
//		DuplexServerInputPort<ByteBuffer> sessionPort =	new ADuplexBufferSessionPortFullP2P("localhost", 
//				"" + ASessionsServer.SESSION_SERVER_PORT, ASessionsServer.SESSION_SERVER_NAME, "Test Session", anId, aName);
//		PrintingReplyingReceiveListener printingReplyingReceiveListener = new PrintingReplyingReceiveListener(sessionPort);		
		ConnectionListener connectListener = new ASendingConnectListener(sessionPort);
//		sessionPort.addConnectListener(printingReplyingReceiveListener);
		sessionPort.addConnectionListener(connectListener);
		sessionPort.addReceiveListener(new ABufferDuplexReceiveListener());
//		serverInputPort.addDisconnectListener(echoingReceiveListener);
//		sessionPort.addReceiveListener(printingReplyingReceiveListener);	
		sessionPort.connect();		
	}

}
