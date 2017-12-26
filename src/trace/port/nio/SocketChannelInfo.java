package trace.port.nio;

import java.net.SocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;
import java.nio.channels.spi.AbstractSelectableChannel;

import inputport.rpc.RemoteCall;
import trace.port.rpc.ReceivedCallEndedOld;
import util.trace.TraceableInfo;

public abstract class SocketChannelInfo extends TraceableInfo {	
	protected AbstractSelectableChannel socketChannel;
	public SocketChannelInfo(String aMessage, Object aFinder,
			AbstractSelectableChannel aSocketChannel) {
		super(aMessage, aFinder);
		socketChannel = aSocketChannel;
	}	
}
