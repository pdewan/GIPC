package util.trace.port.buffer;

import java.nio.ByteBuffer;

import inputport.datacomm.NamingSender;
import util.trace.port.MessageSendInfo;

public class TrapperBufferSendInitiated extends MessageSendInfo {
	ByteBuffer byteBufferMessage;
	
	public TrapperBufferSendInitiated(NamingSender aSource, NamingSender aDestination, String aRemoteEndPoint, ByteBuffer aSendMessage) {
		super("ByteBuffer: " + aSendMessage + " with remote end point: " + aRemoteEndPoint + 
    			" forwarded by " +  aSource + " to " + aDestination, aSource, aDestination, aRemoteEndPoint);
		byteBufferMessage = aSendMessage;
	}
	public TrapperBufferSendInitiated(String aMessage, NamingSender aSource, NamingSender aDestination, String aRemoteEndPoint, ByteBuffer aSendMessage) {
		super (aMessage, aSource, aDestination, aRemoteEndPoint );
		byteBufferMessage = aSendMessage;
	}
	
	public ByteBuffer getByteBufferMessage() {
		return byteBufferMessage;
	}
	
	public static TrapperBufferSendInitiated newCase(NamingSender aSource, NamingSender aDestination, String aRemoteEndPoint, ByteBuffer aSendMessage) {
//    	String aMessage = "ByteBuffer: " + aSendMessage + " with remote end point: " + aRemoteEndPoint + 
//    			" forwarded by " +  aSource + " to " + aDestination ;
		String aMessage = aRemoteEndPoint + "->" + aSendMessage;
		TrapperBufferSendInitiated retVal = new TrapperBufferSendInitiated(aMessage, aSource, aDestination, aRemoteEndPoint, aSendMessage);
		retVal.announce();
		return retVal;
	}

}
