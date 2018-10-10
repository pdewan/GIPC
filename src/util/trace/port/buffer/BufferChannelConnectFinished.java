package util.trace.port.buffer;

import inputport.ConnectionManager;
import inputport.ConnectionType;
import util.trace.TraceableInfo;

public class BufferChannelConnectFinished extends TraceableInfo {	
	public BufferChannelConnectFinished(String aMessage, Object aFinder,
			ConnectionManager aBufferChannel,  ConnectionType aConnectionTyoe, String aLocalEnd, String aRemoteEnd) {
		super(aMessage, aFinder);
	}
	public static BufferChannelConnectFinished newCase(Object aSource, 			
			ConnectionManager aBufferChannel, ConnectionType aConnectionTyoe, String aLocalEnd, String aRemoteEnd) {    	
		String aMessage =
				aLocalEnd + "<-->" + aRemoteEnd +
				" " +
					aConnectionTyoe;
		BufferChannelConnectFinished retVal = new BufferChannelConnectFinished(aMessage, 
				aSource, aBufferChannel, aConnectionTyoe, aLocalEnd, aRemoteEnd);
    	retVal.announce();
    	return retVal;
	}
}
