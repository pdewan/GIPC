package port.trace.buffer;

import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;

import inputport.ConnectionManager;
import inputport.rpc.RemoteCall;
import port.trace.rpc.ReceivedCallEndedOld;
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
