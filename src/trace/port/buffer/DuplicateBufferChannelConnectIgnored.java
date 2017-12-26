package trace.port.buffer;

import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;

import inputport.ConnectionManager;
import inputport.rpc.RemoteCall;
import trace.port.rpc.ReceivedCallEndedOld;
import util.trace.TraceableInfo;

public class DuplicateBufferChannelConnectIgnored extends TraceableInfo {	
	public DuplicateBufferChannelConnectIgnored(String aMessage, Object aFinder,
			ConnectionManager aBufferChannel) {
		super(aMessage, aFinder);
	}
	public static DuplicateBufferChannelConnectIgnored newCase(Object aSource, 			
			ConnectionManager aBufferChannel) {    	
		String aMessage =
				" " +
				 aBufferChannel;
		DuplicateBufferChannelConnectIgnored retVal = new DuplicateBufferChannelConnectIgnored(aMessage, 
				aSource, aBufferChannel);
    	retVal.announce();
    	return retVal;
	}
}
