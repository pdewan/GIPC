package port.sessionserver.example;

import inputport.rpc.DirectedRPCProxyGenerator;
import inputport.rpc.duplex.DuplexRPCClientInputPort;
import inputport.rpc.duplex.DuplexRPCInputPortSelector;
import inputport.rpc.group.GroupRPCInputPortSelector;
import inputport.rpc.group.GroupRPCServerInputPort;
import port.old.Adder;
import port.old.AnAdder;
import port.sessionserver.AServerPortDescription;
import port.sessionserver.ServerPortDescription;
import port.sessionserver.SessionObserver;
import port.sessionserver.SessionServer;
import util.trace.Tracer;


public class AnOldSessionClientLauncher {
	public static int SESSION_SERVER_PORT = 9090;
	public static String SESSION_SERVER_NAME = "Sessions Server";
	public static void launch (String myHost, String myID, String myName) {
		GroupRPCServerInputPort serverInputPort = GroupRPCInputPortSelector.createGroupRPCServerInputPort(myID, myName);
		Adder adder = new AnAdder();
		serverInputPort.register(Adder.class, adder);
		serverInputPort.connect();
//		SessionObserver observer = new APrintingSessionObserver();
		SessionObserver observer = new AJoinerConnectingSessionObserver(myName);
		ServerPortDescription sessionClientDescription = new AServerPortDescription(myHost, myID, myName);
		DuplexRPCClientInputPort clientInputPort = DuplexRPCInputPortSelector.createDuplexRPCClientInputPort("localhost", 
				"" + SESSION_SERVER_PORT, SESSION_SERVER_NAME,  myName);		
		clientInputPort.connect();
		try {
			SessionServer sessionServerProxy = (SessionServer) DirectedRPCProxyGenerator.generateRPCProxy(clientInputPort, null,
					SessionServer.class, null);
			Object retVal = sessionServerProxy.join("Test Session", sessionClientDescription, observer);
			System.out.println(retVal);

		} catch (Exception e) {
			e.printStackTrace();
		}		

	}
	
	public static void main (String[] args) {
		Tracer.showInfo(true);
		launch ("localhost", "9091", "test client");
	}

}
