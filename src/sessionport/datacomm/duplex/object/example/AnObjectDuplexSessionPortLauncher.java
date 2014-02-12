package sessionport.datacomm.duplex.object.example;

import port.ParticipantChoice;
import inputport.ConnectionListener;
import sessionport.datacomm.duplex.DuplexSessionPort;
import sessionport.datacomm.duplex.object.AnObjectDuplexReceiveListener;
import sessionport.datacomm.duplex.object.ObjectDuplexSessionPortSelector;
import util.trace.Tracer;


public class AnObjectDuplexSessionPortLauncher {
	public static int SESSION_SERVER_PORT = 9090;
	public static String SESSION_SERVER_NAME = "Session Server";
	public static void launchSessionPartipant( String anId, String aName, ParticipantChoice aJoinChoice) {
//		Tracer.showInfo(true);
		DuplexSessionPort<Object> sessionPort = ObjectDuplexSessionPortSelector.createObjectDuplexSessionPort("localhost", 
				"" + SESSION_SERVER_PORT, SESSION_SERVER_NAME, "Test Session", anId, aName, aJoinChoice
				);
			

		ConnectionListener connectListener = new AnObjectSendingConnectListener(sessionPort);
		sessionPort.addConnectionListener(connectListener);
		sessionPort.addReceiveListener(new AnObjectDuplexReceiveListener());
	
		sessionPort.connect();		
	}

}
