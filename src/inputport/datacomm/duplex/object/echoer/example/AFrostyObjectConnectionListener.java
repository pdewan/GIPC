package inputport.datacomm.duplex.object.echoer.example;

import inputport.ConnectionType;
import inputport.datacomm.duplex.DuplexInputPort;
import inputport.datacomm.duplex.buffer.echoer.example.AFrostyBufferConnectionListener;
import port.ATracingConnectionListener;

public class AFrostyObjectConnectionListener extends ATracingConnectionListener {
	DuplexInputPort<Object> duplexInputPort;
	public AFrostyObjectConnectionListener(DuplexInputPort<Object> anInputPort) {
		super(anInputPort);
		duplexInputPort = anInputPort;
	}
	@Override
	public void connected(String aRemoteEnd, ConnectionType aConnectionType) {
		super.connected(aRemoteEnd, aConnectionType);
		duplexInputPort.send(aRemoteEnd, AFrostyBufferConnectionListener.CONNECT_MESSAGE);
	}
}
