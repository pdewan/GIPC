package sessionport.rpc.duplex.example;

import inputport.ConnectionListener;
import port.ParticipantChoice;
import port.old.Adder;
import port.old.AnAdder;
import sessionport.rpc.duplex.ACallingConnectListener;
import sessionport.rpc.duplex.DuplexRPCSessionPort;
import sessionport.rpc.duplex.DuplexRPCSessionPortSelector;


public class ADuplexRPCSessionPortLauncher {
	static final int SESSION_SERVER_PORT = 9090;
	static final String SESSION_SERVER_NAME = "SESSION SERVER";

	public static void launchSessionPartipant( String anId, String aName, ParticipantChoice aChoice) {
//		Tracer.showInfo(true);
		DuplexRPCSessionPort sessionPort = DuplexRPCSessionPortSelector.createDuplexRPCSessionPort("localhost", 
				"" + SESSION_SERVER_PORT, SESSION_SERVER_NAME, "Test Session", anId, aName,
				aChoice
				);	
		ConnectionListener connectListener = new ACallingConnectListener(sessionPort);
		sessionPort.addConnectionListener(connectListener);
		Adder adder = new AnAdder();
		sessionPort.register(Adder.class, adder);
		sessionPort.connect();		
	}
	

}
