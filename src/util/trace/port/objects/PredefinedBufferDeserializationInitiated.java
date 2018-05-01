package util.trace.port.objects;

import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;

import inputport.ConnectionManager;
import inputport.rpc.RemoteCall;
import util.trace.TraceableInfo;
import util.trace.port.rpc.ReceivedCallEndedOld;

public class PredefinedBufferDeserializationInitiated extends TraceableInfo {	
	public PredefinedBufferDeserializationInitiated(String aMessage, Object aFinder,
			String aSource,
			ByteBuffer aByteBuffer, Object aDelegate) {
		super(aMessage, aFinder);
	}
	public static PredefinedBufferDeserializationInitiated newCase(Object aFinder, 
			String aSource,
			ByteBuffer aByteBuffer, Object aDelegate) {    	
		String aMessage =
				aSource + "->" +
				
				aByteBuffer + 
				"( via " + aDelegate + ")";
		PredefinedBufferDeserializationInitiated retVal = new PredefinedBufferDeserializationInitiated(aMessage, 
				aFinder,
				aSource,  aByteBuffer, aDelegate);
    	retVal.announce();
    	return retVal;
	}
}
