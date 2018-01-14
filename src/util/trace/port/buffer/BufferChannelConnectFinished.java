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
