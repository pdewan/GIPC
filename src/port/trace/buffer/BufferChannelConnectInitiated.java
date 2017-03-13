package port.trace.buffer;

import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;

import inputport.ConnectionManager;
import inputport.rpc.RemoteCall;
import port.trace.rpc.ReceivedCallEndedOld;
import util.trace.TraceableInfo;

public class BufferChannelConnectInitiated extends TraceableInfo {	
	public BufferChannelConnectInitiated(String aMessage, Object aFinder,
			ConnectionManager aBufferChannel) {
		super(aMessage, aFinder);
	}
	public static BufferChannelConnectInitiated newCase(Object aSource, 			
			ConnectionManager aBufferChannel) {    	
		String aMessage =
				" " +
				 aBufferChannel;
		BufferChannelConnectInitiated retVal = new BufferChannelConnectInitiated(aMessage, 
				aSource, aBufferChannel);
    	retVal.announce();
    	return retVal;
	}
}
