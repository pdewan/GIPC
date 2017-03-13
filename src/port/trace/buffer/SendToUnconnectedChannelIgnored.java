package port.trace.buffer;

import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;

import inputport.datacomm.simplex.buffer.SimplexBufferClientInputPortDriver;
import inputport.rpc.RemoteCall;
import port.trace.rpc.ReceivedCallEndedOld;
import util.trace.TraceableInfo;

public class SendToUnconnectedChannelIgnored extends TraceableInfo {	
	public SendToUnconnectedChannelIgnored(String aMessage, Object aFinder,
			
			ByteBuffer aByteBuffer) {
		super(aMessage, aFinder);
	}
	public static SendToUnconnectedChannelIgnored newCase(Object aSource, 			
			ByteBuffer aByteBuffer) {    	
		String aMessage =
				"" +
				aByteBuffer ;
		SendToUnconnectedChannelIgnored retVal = new SendToUnconnectedChannelIgnored(aMessage, 
				aSource, aByteBuffer);
    	retVal.announce();
    	return retVal;
	}
}
