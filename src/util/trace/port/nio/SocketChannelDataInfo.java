package util.trace.port.nio;

import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;

import inputport.rpc.RemoteCall;
import util.trace.TraceableInfo;
import util.trace.port.rpc.ReceivedCallEndedOld;

public abstract class SocketChannelDataInfo extends SocketChannelInfo {	
	protected ByteBuffer byteBuffer;
	public SocketChannelDataInfo(String aMessage, Object aFinder,
			SocketChannel aSocketChannel,
			ByteBuffer aByteBuffer) {
		super(aMessage, aFinder, aSocketChannel);
		byteBuffer = aByteBuffer;
	}
	
}
