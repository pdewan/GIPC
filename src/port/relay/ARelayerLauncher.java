package port.relay;
import inputport.ConnectionListener;
import inputport.InputPort;
import inputport.datacomm.simplex.SimplexServerInputPort;
import java.nio.ByteBuffer;

import port.ATracingConnectionListener;
import port.AnAbstractPortLauncher;
import port.PortLauncherSupport;
public class ARelayerLauncher extends AnAbstractPortLauncher implements RelayerLauncher{
	protected String relayerServerId;
	protected String relayerServerName;
	public  ARelayerLauncher (String aRelayerId, String aRelayerName) {
		relayerServerId = aRelayerId;
		relayerServerName = aRelayerName;
	}	
	protected PortLauncherSupport getPortLauncherSupport() {
		return new ARelayerLauncherSupport();
	}
	protected ConnectionListener getConnectionListener (InputPort aServerInputPort) {
		return new ATracingConnectionListener((SimplexServerInputPort<ByteBuffer>) aServerInputPort);
	}
	protected InputPort getPort() {
		return RelayerPortSelector.createRelayerPort(relayerServerId, relayerServerName);
	}
	public static void main (String args[]) {	
		(new ARelayerLauncher(RELAYER_ID, RELAYER_NAME)).launch();
	}
//	@Override
//	protected ReceiveListener getReceiveListener (InputPort aServerInputPort) {
//		return new AnEchoingBufferReceiveListener(aServerInputPort);
//	}	
	
}
