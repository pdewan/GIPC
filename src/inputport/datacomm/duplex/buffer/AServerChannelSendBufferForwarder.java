package inputport.datacomm.duplex.buffer;

import java.nio.ByteBuffer;

import inputport.datacomm.NamingSender;
import util.trace.Tracer;

public class AServerChannelSendBufferForwarder<RequestChannelType, ChannelType>  implements NamingSender<ByteBuffer>{
	DuplexServerInputPortDriver<RequestChannelType, ChannelType> duplexServerInputDriver;
	DuplexBufferGenericServerInputPort<RequestChannelType, ChannelType> bufferDuplexServerInputPort;
	public AServerChannelSendBufferForwarder(DuplexServerInputPortDriver<RequestChannelType, ChannelType> aDuplexServerInputDriver,
			DuplexBufferGenericServerInputPort<RequestChannelType, ChannelType> aBufferDuplexServerInputPort) {
		duplexServerInputDriver = aDuplexServerInputDriver;
		bufferDuplexServerInputPort = aBufferDuplexServerInputPort;
	}

	@Override
	public void send(String remoteName, ByteBuffer message) {

		Tracer.info(this, "Forwarding server channel buffer " + message + " to name: " + remoteName );
		duplexServerInputDriver.send(remoteName, message);
	}

}
