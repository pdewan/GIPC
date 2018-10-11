package port.sessionserver.relay;

import java.nio.ByteBuffer;

import inputport.ConnectionListener;
import inputport.InputPort;
import inputport.datacomm.ReceiveListener;
import inputport.datacomm.simplex.SimplexServerInputPort;
import inputport.datacomm.simplex.buffer.AnEchoingBufferReceiveListener;
import port.ATracingConnectionListener;
import port.AnAbstractPortLauncher;
import port.PortLauncherSupport;
import port.sessionserver.ASessionServerLauncher;
import port.sessionserver.relay.example.ASessionAndRelayServerClientLauncher;




public class ASessionServerRelayerLauncher extends AnAbstractPortLauncher {
	String relayerServerId;
	String relayerServerName;
	String sessionServerHost;
	String sessionServerId;
	String sessionServerName;
	public static String RELAYER_ID = "9200";
	public static String RELAYER_NAME = "Relayer";
	public static String SESSION_SERVER_HOST = "localhost";

	

	public static void main (String args[]) {	
//		(new ARelayerLauncher("" + SESSION_SERVER_PORT, SESSION_SERVER_NAME)).launch();
		(new ASessionServerRelayerLauncher(RELAYER_ID, RELAYER_NAME, SESSION_SERVER_HOST, ASessionServerLauncher.SESSION_SERVER_ID, ASessionServerLauncher.SESSION_SERVER_NAME)).launch();

	}
	public  ASessionServerRelayerLauncher (String aRelayerId, String aRelayerName, String aSessionServerHost, String aSessionServerId, String aSessionServerName) {
		relayerServerId = aRelayerId;
		relayerServerName = aRelayerName;
		sessionServerHost = aSessionServerHost;
		sessionServerId = aSessionServerId;
		sessionServerName = aSessionServerName;
	}

	
	protected PortLauncherSupport getPortLauncherSupport() {
		return new ASessionServerRelayerLauncherSupport();
	}

	@Override
	protected ConnectionListener getConnectionListener (InputPort aServerInputPort) {
		return new ATracingConnectionListener((SimplexServerInputPort<ByteBuffer>) aServerInputPort);
	}
	@Override
	protected ReceiveListener getReceiveListener (InputPort aServerInputPort) {
		return new AnEchoingBufferReceiveListener(aServerInputPort);
	}	
	@Override
	protected InputPort getPort() {
		return SessionServerRelayerPortSelector.createRelayerPort(relayerServerId, relayerServerName, sessionServerHost, sessionServerId, sessionServerName, ASessionAndRelayServerClientLauncher.SESSION_NAME);
	}
	
	
	
	
}
