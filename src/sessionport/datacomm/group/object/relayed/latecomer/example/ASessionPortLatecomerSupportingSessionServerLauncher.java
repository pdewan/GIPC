package sessionport.datacomm.group.object.relayed.latecomer.example;

import inputport.InputPort;
import port.PortLauncherSupport;
import port.sessionserver.ASessionServerLauncher;
import port.sessionserver.relay.late.ALatecomerServerLauncherSupport;
import port.sessionserver.relay.late.LatecomerRelayerAndSessionServerSelector;



public class ASessionPortLatecomerSupportingSessionServerLauncher extends ASessionServerLauncher {
	
	public static void main (String args[]) {	
		(new ASessionPortLatecomerSupportingSessionServerLauncher("" + SESSION_SERVER_PORT, SESSION_SERVER_NAME)).launch();
	}
	public ASessionPortLatecomerSupportingSessionServerLauncher(String aSessionServerId, String aSessionServerName) {
		super(aSessionServerId, aSessionServerName );
//		sessionServerName = aSessionServerName;
//		sessionServerId = aSessionServerId;
	}
	protected PortLauncherSupport getPortLauncherSupport() {
		return new ALatecomerServerLauncherSupport();
	}
	@Override
	protected InputPort getPort() {
		return LatecomerRelayerAndSessionServerSelector.
		createLatecomerSessionsServerAndRelayer(
				SESSION_SERVER_ID, 
				SESSION_SERVER_NAME);
	}

//	@Override
//	public ConnectionListener createConnectionListener (InputPort aServerInputPort) {
//		return new ATracingConnectionListener((SimplexServerInputPort<ByteBuffer>) aServerInputPort);
//	}
//	@Override
//	public ReceiveListener createReceiveListener (InputPort aServerInputPort) {
//		return new AnEchoingReceiveListener(aServerInputPort);
//	}	
//	@Override
//	public InputPort createPort() {
//		return SessionServerSelector.
//		createSessionServer(sessionServerId, sessionServerName);
//	}
//	
	
	
	
}
