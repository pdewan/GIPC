package port.sessionserver.relay.mvc.example;

import inputport.ConnectionListenerWithPort;
import inputport.ConnectionType;
import inputport.rpc.duplex.DuplexRPCClientInputPort;
import port.sessionserver.ServerPortDescription;

public class AnOldRelayerConnectionListener extends AServerConnectingConnectionListener {

	public AnOldRelayerConnectionListener(
			ServerPortDescription aServerPortDescription, String aClientName,
			ConnectionListenerWithPort aConnectionListener) {
		super(aServerPortDescription, aClientName, aConnectionListener);
	}
	@Override
	public void connected(String aRemoteEnd, ConnectionType aConnectionType) {
		ConnectionListenerWithPort mvcConnectionListener = createMVCConnectionListener();
		nextConnectionListener = mvcConnectionListener;
		super.connected(aRemoteEnd, aConnectionType); 
	}
	protected ConnectionListenerWithPort createMVCConnectionListener() {
		return new AnMVCServerConnectionListenerAndLauncher((DuplexRPCClientInputPort) inputPort);
	}

}
