package inputport.datacomm.simplex.buffer;

import java.nio.ByteBuffer;

import inputport.InputPort;
import inputport.datacomm.NamingSender;
import util.trace.Tracer;


public class AClientChannelSendBufferForwarder<ChannelType>  implements NamingSender<ByteBuffer>{
	SimplexBufferClientInputPortDriver<ChannelType> clientInputDriver;
	public AClientChannelSendBufferForwarder(InputPort anInputPort, SimplexBufferClientInputPortDriver<ChannelType> aClientInputDriver) {
		clientInputDriver = aClientInputDriver;
	}
	@Override
	public void send(String remoteName, ByteBuffer message) {
//		BufferChannelSendInitiated.newCase(this, clientInputDriver, message);
		Tracer.info(this, "Forwarding client channel message: " + message + " to:" + remoteName );
		clientInputDriver.send(remoteName, message);
	}
}
