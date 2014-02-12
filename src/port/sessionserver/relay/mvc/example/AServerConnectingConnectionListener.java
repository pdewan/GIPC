package port.sessionserver.relay.mvc.example;

import inputport.ConnectionListenerWithPort;
import inputport.ConnectionType;
import inputport.InputPort;
import inputport.rpc.duplex.DuplexRPCClientInputPort;
import inputport.rpc.duplex.DuplexRPCInputPortSelector;
import port.ATracingConnectionListener;
import port.sessionserver.ServerPortDescription;

public class AServerConnectingConnectionListener extends ATracingConnectionListener implements ServerConnectingConnectionListener {
	String clientName;
	ServerPortDescription nextServerPortDescription;
	ConnectionListenerWithPort nextConnectionListener;	
	public AServerConnectingConnectionListener(ServerPortDescription aServerPortDescription, String aClientName, ConnectionListenerWithPort aConnectionListener) {
		nextServerPortDescription = aServerPortDescription;
		clientName = aClientName;
		nextConnectionListener = aConnectionListener;
	}	
	@Override
	public void connected(String aRemoteEndName, ConnectionType aConnectionType) {
//		super.connected(aRemoteEndName, aConnectionType);
		// no super call as this conected may be a dummy call
		DuplexRPCClientInputPort nextPort = DuplexRPCInputPortSelector.createDuplexRPCClientInputPort(
				nextServerPortDescription.getHost(), nextServerPortDescription.getID(), nextServerPortDescription.getName(), clientName);
		nextConnectionListener.initInputPort(nextPort);
		nextPort.addConnectionListener(nextConnectionListener);
		nextPort.connect();
	}
	@Override
	public void initInputPort(InputPort anInputPort) {
		inputPort = anInputPort;	
	}
	public void initNextServerPortDescription(ServerPortDescription aServerPortDescription) {
		nextServerPortDescription = aServerPortDescription;
	}
	public void initNextConnectionListener(ConnectionListenerWithPort aConnectionListener) {
		nextConnectionListener = aConnectionListener;
	}	
}
