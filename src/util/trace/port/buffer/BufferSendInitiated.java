package util.trace.port.buffer;

import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;

import inputport.ConnectionManager;
import inputport.rpc.RemoteCall;
import util.trace.TraceableInfo;
import util.trace.port.rpc.ReceivedCallEndedOld;

public class BufferSendInitiated extends TraceableInfo {	
	public BufferSendInitiated(String aMessage, Object aFinder,
			String aSource,
			String aDestination,
			ByteBuffer aByteBuffer,
			Object aBufferChannel) {
		super(aMessage, aFinder);
	}
	public static BufferSendInitiated newCase(Object aFinder, 
			String aSource,
			String aDestination,
			ByteBuffer aByteBuffer,
			Object aBufferChannel) {    	
		String aMessage =
				aSource + "->" + aDestination + " " +
				
				aByteBuffer +
				"(" +
				 aBufferChannel + ")";
		BufferSendInitiated retVal = new BufferSendInitiated(aMessage, 
				aFinder,
				aSource, aDestination, aByteBuffer, aBufferChannel);
    	retVal.announce();
    	return retVal;
	}
}
