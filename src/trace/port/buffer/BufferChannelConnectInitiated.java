package trace.port.buffer;

import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;

import inputport.ConnectionManager;
import inputport.rpc.RemoteCall;
import trace.port.rpc.ReceivedCallEndedOld;
import util.trace.TraceableInfo;

public class BufferChannelConnectInitiated extends TraceableInfo {	
	public BufferChannelConnectInitiated(String aMessage, Object aFinder,
			ConnectionManager aBufferChannel, String aLocalEnd, String aRemoteEnd) {
		super(aMessage, aFinder);
	}
	public static BufferChannelConnectInitiated newCase(Object aSource, 			
			ConnectionManager aBufferChannel, String aLocalEnd, String aRemoteEnd) {    	
		String aMessage =
				aLocalEnd + "<-->" + aRemoteEnd ;
		BufferChannelConnectInitiated retVal = new BufferChannelConnectInitiated(aMessage, 
				aSource, aBufferChannel, aLocalEnd, aRemoteEnd);
    	retVal.announce();
    	return retVal;
	}
}
