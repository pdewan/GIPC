package util.trace.port.buffer;

import util.trace.TraceableInfo;

public class BufferClientChannelLocallyConnected extends TraceableInfo {	
	public BufferClientChannelLocallyConnected(String aMessage, Object aFinder,
			 String aLocalEnd, String aRemoteEnd) {
		super(aMessage, aFinder);
	}
	public static BufferClientChannelLocallyConnected newCase(Object aSource, 			
			 String aLocalEnd, String aRemoteEnd) {    	
		String aMessage =
				aLocalEnd + "<-->" + aRemoteEnd ;
		BufferClientChannelLocallyConnected retVal = new BufferClientChannelLocallyConnected(aMessage, 
				aSource, aLocalEnd, aRemoteEnd);
    	retVal.announce();
    	return retVal;
	}
}
