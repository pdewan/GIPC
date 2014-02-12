package port.sessionserver.relay.mvc.example;


import java.net.InetAddress;
import java.util.List;

import inputport.ConnectionListener;
import inputport.ConnectionType;
import inputport.InputPort;
import inputport.rpc.duplex.ADuplexRPCInputPortLauncherSupport;
import inputport.rpc.duplex.DuplexRPCClientInputPort;
import inputport.rpc.duplex.DuplexRPCInputPortSelector;
import inputport.rpc.simplex.mvc.example.SimplexRPCClientMVCLauncher;
import inputport.rpc.simplex.mvc.example.SimplexRPCServerMVCLauncher;
import port.AnAbstractPortLauncher;
import port.PortLauncherSupport;
import port.relay.RelayerLauncher;
import port.sessionserver.AServerPortDescription;
import port.sessionserver.JoinInfo;
import port.sessionserver.ServerPortDescription;
import port.sessionserver.SessionServerLauncher;
import port.sessionserver.example.APrintingSessionObserver;
import port.sessionserver.relay.ARelayerSupportingSessionServer;
import port.sessionserver.relay.RelayerSupportingSessionServer;
import port.sessionserverAndRelay.mvc.example.UpperCaseSession;
import util.trace.Tracer;

public class AliceThreeDepPortClientLauncher   {
	
	public static final String  USER_NAME = "Alice";

	public static void main (String[] args) {		
		(new AThreeDepPortClientLauncher(
				USER_NAME, 
//				"localhost", SimplexRPCServerMVCLauncher.MVC_SERVER_ID, SimplexRPCServerMVCLauncher.MVC_SERVER_NAME,
//				"localhost", RelayerLauncher.RELAYER_ID, RelayerLauncher.RELAYER_NAME,
				"localhost", SessionServerLauncher.SESSION_SERVER_ID, SessionServerLauncher.SESSION_SERVER_NAME, UpperCaseSession.SESSION_NAME
				)).launch();
	}
}
