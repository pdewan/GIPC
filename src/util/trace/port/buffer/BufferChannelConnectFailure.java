package util.trace.port.buffer;

import inputport.datacomm.simplex.buffer.SimplexBufferClientInputPortDriver;
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
