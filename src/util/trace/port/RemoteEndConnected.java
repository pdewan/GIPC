package util.trace.port;

import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;

import inputport.ConnectionManager;
import inputport.ConnectionType;
import inputport.rpc.RemoteCall;
import util.trace.TraceableInfo;
import util.trace.port.rpc.ReceivedCallEndedOld;

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
