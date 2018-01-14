package util.trace.port.buffer;

import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;

import inputport.ConnectionManager;
import inputport.ConnectionType;
import inputport.rpc.RemoteCall;
import util.trace.TraceableInfo;
import util.trace.port.rpc.ReceivedCallEndedOld;

public class BufferChannelDisconnected extends TraceableInfo {	
	public BufferChannelDisconnected(String aMessage, Object aFinder,
			Object aSource,
			String aRemoteEndName,
			boolean anExplicitDisconnection, 
			String anExplanation, ConnectionType aConnectionType) {

		super(aMessage, aFinder);
	}
	public static BufferChannelDisconnected newCase(			
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
		BufferChannelDisconnected retVal = new BufferChannelDisconnected(aMessage, aFinder,
				aSource, aRemoteEndName, anExplicitDisconnection, anExplanation, aConnectionType);
    	retVal.announce();
    	return retVal;
	}
}
