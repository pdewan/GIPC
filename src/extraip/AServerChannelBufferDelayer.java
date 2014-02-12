package extraip;

import inputport.datacomm.NamingSender;
import inputport.datacomm.duplex.buffer.AServerChannelSendBufferForwarder;
import inputport.datacomm.duplex.buffer.DuplexBufferGenericServerInputPort;
import inputport.datacomm.duplex.buffer.DuplexServerInputPortDriver;

import java.nio.ByteBuffer;




public class AServerChannelBufferDelayer<RequestChannelType,ChannelType> extends AnAbstractDelayer implements NamingSender<ByteBuffer>{
//	DuplexServerInputPortDriver<ChannelType> duplexServerInputDriver;
//	BufferGenericDuplexServerInputPort<ChannelType> bufferDuplexServerInputPort;
	NamingSender<ByteBuffer> forwarder;
	public AServerChannelBufferDelayer(DuplexServerInputPortDriver<RequestChannelType,ChannelType> aDuplexServerInputDriver,
			DuplexBufferGenericServerInputPort<RequestChannelType,ChannelType> aBufferDuplexServerInputPort) {
		forwarder = new AServerChannelSendBufferForwarder<RequestChannelType,ChannelType>(aDuplexServerInputDriver, aBufferDuplexServerInputPort);
		
	}
//
//	@Override
//	public void send(String remoteName, Object message) {
//		ChannelType channel = bufferDuplexServerInputPort.getChannel(remoteName);
//		if (channel == null) throw new SendToUnkonwnRemoteNameException();
//		Message.info("Forwarding server channel bufer " + message + " to name: " + remoteName + " on channel: " + channel);
//		duplexServerInputDriver.send(channel, (ByteBuffer) message);
//	}

}
