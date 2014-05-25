package port.sessionserver;

import inputport.ConnectionListener;
import inputport.InputPort;
import inputport.datacomm.simplex.SimplexServerInputPort;

import java.nio.ByteBuffer;

import port.ATracingConnectionListener;
import port.AnAbstractPortLauncher;
import port.PortLauncherSupport;





public class ASessionServerLauncher extends AnAbstractPortLauncher implements SessionServerLauncher {
	protected String sessionServerName;
	protected String sessionServerId;
	protected String logicalServerName;	
	public ASessionServerLauncher(String aSessionServerId, String aSessionServerName, String aLogicalServerName) {
		sessionServerName = aSessionServerName;
		sessionServerId = aSessionServerId;
		logicalServerName = aLogicalServerName;
	}
	public ASessionServerLauncher(String aSessionServerId, String aSessionServerName) {
		sessionServerName = aSessionServerName;
		sessionServerId = aSessionServerId;
		logicalServerName = aSessionServerName;
	}
	protected PortLauncherSupport getPortLauncherSupport() {
		return new ASessionServerLauncherSupport();
	}
	protected ConnectionListener getConnectionListener (InputPort aServerInputPort) {
		return new ATracingConnectionListener((SimplexServerInputPort<ByteBuffer>) aServerInputPort);
	}
	protected InputPort getPort() {
		return SessionServerSelector.createSessionServerPort(sessionServerId, sessionServerName, logicalServerName);
	}
	public static void main (String args[]) {	
		(new ASessionServerLauncher("" + SESSION_SERVER_PORT, SESSION_SERVER_NAME)).launch();
	}
	
//	@Override
//	protected ReceiveListener getReceiveListener (InputPort aServerInputPort) {
//		return new AnEchoingBufferReceiveListener(aServerInputPort);
//	}	
	
	
}
