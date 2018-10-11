package util.trace.port.buffer;

import inputport.ConnectionManager;
import util.trace.TraceableInfo;

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
