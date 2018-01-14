package util.trace.port.buffer;

import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;

import inputport.ConnectionManager;
import inputport.rpc.RemoteCall;
import util.trace.TraceableInfo;
import util.trace.port.rpc.ReceivedCallEndedOld;

public class BufferChannelDisconnectInitiated extends TraceableInfo {	
	public BufferChannelDisconnectInitiated(String aMessage, Object aFinder,
			ConnectionManager aBufferChannel, Object anOtherEnd) {
		super(aMessage, aFinder);
	}
	public static BufferChannelDisconnectInitiated newCase(Object aSource, 			
			ConnectionManager aBufferChannel, Object anOtherEnd) {    	
		String aMessage =
				" " +
				 anOtherEnd;
		BufferChannelDisconnectInitiated retVal = new BufferChannelDisconnectInitiated(aMessage, 
				aSource, aBufferChannel, anOtherEnd);
    	retVal.announce();
    	return retVal;
	}
}
