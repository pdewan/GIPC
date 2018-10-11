package util.trace.port.nio;

import java.nio.channels.spi.AbstractSelectableChannel;

import util.trace.TraceableInfo;

public abstract class SocketChannelInfo extends TraceableInfo {	
	protected AbstractSelectableChannel socketChannel;
	public SocketChannelInfo(String aMessage, Object aFinder,
			AbstractSelectableChannel aSocketChannel) {
		super(aMessage, aFinder);
		socketChannel = aSocketChannel;
	}	
}
