package port.trace;

import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;

import inputport.ConnectionManager;
import inputport.ConnectionType;
import inputport.rpc.RemoteCall;
import port.trace.rpc.ReceivedCallEndedOld;
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
