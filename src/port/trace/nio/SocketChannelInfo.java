package port.trace.nio;

import java.net.SocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;

import inputport.rpc.RemoteCall;
import port.trace.ReceivedCallEnded;
import util.trace.TraceableInfo;

public abstract class SocketChannelInfo extends TraceableInfo {	
	protected SocketChannel socketChannel;
	public SocketChannelInfo(String aMessage, Object aFinder,
			SocketChannel aSocketChannel) {
		super(aMessage, aFinder);
		socketChannel = aSocketChannel;
	}	
}
