package util.trace.port.nio;

import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;

public abstract class SocketChannelDataInfo extends SocketChannelInfo {	
	protected ByteBuffer byteBuffer;
	public SocketChannelDataInfo(String aMessage, Object aFinder,
			SocketChannel aSocketChannel,
			ByteBuffer aByteBuffer) {
		super(aMessage, aFinder, aSocketChannel);
		byteBuffer = aByteBuffer;
	}
	
}
