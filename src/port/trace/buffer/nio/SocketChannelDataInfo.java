package port.trace.buffer.nio;

import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;

import inputport.rpc.RemoteCall;
import port.trace.rpc.ReceivedCallEndedOld;
import util.trace.TraceableInfo;

public abstract class SocketChannelDataInfo extends SocketChannelInfo {	
	protected ByteBuffer byteBuffer;
	public SocketChannelDataInfo(String aMessage, Object aFinder,
			SocketChannel aSocketChannel,
			ByteBuffer aByteBuffer) {
		super(aMessage, aFinder, aSocketChannel);
		byteBuffer = aByteBuffer;
	}
	
}
