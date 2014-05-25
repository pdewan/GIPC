package port.sessionserver.asymmetricexample;

import port.sessionserver.ASessionServerLauncher;




public class AnExampleSessionServerauncher  {
//	public static int SESSION_SERVER_PORT = 9090;
//	public static String SESSION_SERVER_ID = "" + SESSION_SERVER_PORT;
//	public static String SESSION_SERVER_NAME = "Sessions Server";
//	protected String sessionServerName;
//	protected String sessionServerId;
//	protected String logicalServerName;
	public static void main (String args[]) {	
		ASessionServerLauncher.main(args);
	}
//	public ASessionServerExampleLauncher(String aSessionServerId, String aSessionServerName, String aLogicalServerName) {
//		sessionServerName = aSessionServerName;
//		sessionServerId = aSessionServerId;
//		logicalServerName = aLogicalServerName;
//	}
//	public ASessionServerExampleLauncher(String aSessionServerId, String aSessionServerName) {
//		sessionServerName = aSessionServerName;
//		sessionServerId = aSessionServerId;
//		logicalServerName = aSessionServerName;
//	}
//	protected PortLauncherSupport getPortLauncherSupport() {
//		return new ASessionServerLauncherSupport();
//	}
//
//	@Override
//	protected ConnectionListener getConnectionListener (InputPort aServerInputPort) {
//		return new ATracingConnectionListener((SimplexServerInputPort<ByteBuffer>) aServerInputPort);
//	}
//	@Override
//	protected ReceiveListener getReceiveListener (InputPort aServerInputPort) {
//		return new AnEchoingBufferReceiveListener(aServerInputPort);
//	}	
//	@Override
//	protected InputPort getPort() {
//		return SessionServerSelector.createSessionServerPort(sessionServerId, sessionServerName, logicalServerName);
//	}
//	
//	
	
	
}
