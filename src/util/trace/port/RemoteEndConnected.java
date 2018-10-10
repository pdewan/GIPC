package util.trace.port;

import inputport.ConnectionType;
import util.trace.TraceableInfo;

public class RemoteEndConnected extends TraceableInfo {	
	public RemoteEndConnected(String aMessage, Object aFinder,
			  String aLocalEnd, String aRemoteEnd, ConnectionType aConnectionType) {
		super(aMessage, aFinder);
	}
	public static RemoteEndConnected newCase(Object aSource, 			
			  String aLocalEnd, String aRemoteEnd, ConnectionType aConnectionType) {    	
		String aMessage =
				aLocalEnd + "<-->" + aRemoteEnd +
				" " +
					aConnectionType;
		RemoteEndConnected retVal = new RemoteEndConnected(aMessage, 
				aSource, aLocalEnd,aRemoteEnd, aConnectionType);
    	retVal.announce();
    	return retVal;
	}
}
