package util.trace.port.buffer;

import inputport.ConnectionManager;
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
