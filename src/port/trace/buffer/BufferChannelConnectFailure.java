package port.trace.buffer;

import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;

import inputport.datacomm.simplex.buffer.SimplexBufferClientInputPortDriver;
import inputport.rpc.RemoteCall;
import port.trace.rpc.ReceivedCallEndedOld;
import util.trace.TraceableInfo;

public class BufferChannelConnectFailure extends TraceableInfo {	
	public BufferChannelConnectFailure(String aMessage, Object aFinder,
			SimplexBufferClientInputPortDriver aBufferChannel) {
		super(aMessage, aFinder);
	}
	public static BufferChannelConnectFailure newCase(Object aSource, 			
			SimplexBufferClientInputPortDriver aBufferChannel) {    	
		String aMessage =
				" " +
				 aBufferChannel;
		BufferChannelConnectFailure retVal = new BufferChannelConnectFailure(aMessage, 
				aSource, aBufferChannel);
    	retVal.announce();
    	return retVal;
	}
}
