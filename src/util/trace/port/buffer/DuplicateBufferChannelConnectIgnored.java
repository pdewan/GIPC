package util.trace.port.buffer;

import inputport.ConnectionManager;
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
