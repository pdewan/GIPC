package util.trace.port;

import inputport.ConnectionType;
import util.trace.TraceableInfo;

public class RemoteEndDisconnected extends TraceableInfo {	
	public RemoteEndDisconnected(String aMessage, Object aFinder,
			Object aSource,
			String aRemoteEndName,
			boolean anExplicitDisconnection, 
			String anExplanation, ConnectionType aConnectionType) {

		super(aMessage, aFinder);
	}
	public static RemoteEndDisconnected newCase(			
			Object aFinder,
			Object aSource, 
			String aRemoteEndName,
			boolean anExplicitDisconnection, 
			String anExplanation, ConnectionType aConnectionType) {    	
		String aMessage =
				aSource +
				"<-->" +
				aRemoteEndName +
				" explicit? " +
				anExplicitDisconnection +
				" readon:" +
				anExplanation +
				"(" +
				aConnectionType + 
				")";
		RemoteEndDisconnected retVal = new RemoteEndDisconnected(aMessage, aFinder,
				aSource, aRemoteEndName, anExplicitDisconnection, anExplanation, aConnectionType);
    	retVal.announce();
    	return retVal;
	}
}
