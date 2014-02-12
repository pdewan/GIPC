package inputport.datacomm.duplex.buffer.echoer.example;

import inputport.ConnectionType;
import inputport.datacomm.duplex.DuplexInputPort;

import java.nio.ByteBuffer;

import port.ATracingConnectionListener;

public class AFrostyBufferConnectionListener extends ATracingConnectionListener {
	DuplexInputPort<ByteBuffer> duplexInputPort;
	public static final String CONNECT_MESSAGE = "Stopping by Woods on a Snowy Evening please!";
	public AFrostyBufferConnectionListener(DuplexInputPort anInputPort) {
		super(anInputPort);
		duplexInputPort = anInputPort;
	}
	@Override
	public void connected(String aRemoteEnd, ConnectionType aConnectionType) {
		super.connected(aRemoteEnd, aConnectionType);
		duplexInputPort.send(aRemoteEnd, ByteBuffer.wrap(CONNECT_MESSAGE.getBytes()));
	}
}
