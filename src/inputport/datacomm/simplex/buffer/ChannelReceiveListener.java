package inputport.datacomm.simplex.buffer;

import java.nio.ByteBuffer;

public interface ChannelReceiveListener<MessageChannel> {
	// not making port as an argument as type of port keeps evolving. Better to initialize in constructor.
	// restriction that cannot have a listener for multiple ports, which seems ok.
	public void  messageReceived(MessageChannel aSource, ByteBuffer aMessage);
}
