package trace.port.buffer;

import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;

import inputport.ConnectionManager;
import inputport.rpc.RemoteCall;
import trace.port.rpc.ReceivedCallEndedOld;
import util.trace.TraceableInfo;

public class BufferReplyInitiated extends TraceableInfo {	
	public BufferReplyInitiated(String aMessage, Object aFinder,
			String aSource,
			ByteBuffer aByteBuffer) {
		super(aMessage, aFinder);
	}
	public static BufferReplyInitiated newCase(Object aFinder, 
			String aSource,
			ByteBuffer aByteBuffer) {    	
		String aMessage =
				aSource + "-> last sender" +
				
				aByteBuffer ;;
		BufferReplyInitiated retVal = new BufferReplyInitiated(aMessage, 
				aFinder,
				aSource,  aByteBuffer);
    	retVal.announce();
    	return retVal;
	}
}
