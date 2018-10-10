package extraip;

import java.nio.ByteBuffer;

import inputport.datacomm.NamingSender;
import inputport.datacomm.duplex.buffer.AServerChannelSendBufferForwarderFactory;
import inputport.datacomm.duplex.buffer.DuplexBufferGenericServerInputPort;
import inputport.datacomm.duplex.buffer.DuplexServerInputPortDriver;
import inputport.datacomm.duplex.buffer.ServerChannelBufferSendTrapperFactory;

public class ServerChannelBufferSendTrapperSelector<SocketChannel> {
	static ServerChannelBufferSendTrapperFactory serverChannelBufferSendTrapperFactory = new AServerChannelSendBufferForwarderFactory();
	
	public static ServerChannelBufferSendTrapperFactory getServerChannelBufferSendTrapperFactory() {
		return serverChannelBufferSendTrapperFactory;
	}

	public static void setServerChannelBufferSendTrapperFactory(
			ServerChannelBufferSendTrapperFactory serverChannelBufferSendTrapperFactory) {
		ServerChannelBufferSendTrapperSelector.serverChannelBufferSendTrapperFactory = serverChannelBufferSendTrapperFactory;
	}

	public static NamingSender<ByteBuffer>  createServerChannelBufferSendTrapper(
			//			InputPort anInputPort,
			DuplexBufferGenericServerInputPort aBufferDuplexServerInputPort,
DuplexServerInputPortDriver aDuplexServerInputDriver) {
		return serverChannelBufferSendTrapperFactory.createServerChannelBufferSendTrapper(aBufferDuplexServerInputPort, aDuplexServerInputDriver);
	}

}
