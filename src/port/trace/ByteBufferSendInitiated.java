package port.trace;

import inputport.datacomm.NamingSender;

import java.nio.ByteBuffer;

public class ByteBufferSendInitiated extends MessageSendInfo {
	ByteBuffer byteBufferMessage;
	
	public ByteBufferSendInitiated(NamingSender aSource, NamingSender aDestination, String aRemoteEndPoint, ByteBuffer aSendMessage) {
		super("ByteBuffer: " + aSendMessage + " with remote end point: " + aRemoteEndPoint + 
    			" forwarded by " +  aSource + " to " + aDestination, aSource, aDestination, aRemoteEndPoint);
		byteBufferMessage = aSendMessage;
	}
	public ByteBufferSendInitiated(String aMessage, NamingSender aSource, NamingSender aDestination, String aRemoteEndPoint, ByteBuffer aSendMessage) {
		super (aMessage, aSource, aDestination, aRemoteEndPoint );
		byteBufferMessage = aSendMessage;
	}
	
	public ByteBuffer getByteBufferMessage() {
		return byteBufferMessage;
	}
	
	public static ByteBufferSendInitiated newCase(NamingSender aSource, NamingSender aDestination, String aRemoteEndPoint, ByteBuffer aSendMessage) {
//    	String aMessage = "ByteBuffer: " + aSendMessage + " with remote end point: " + aRemoteEndPoint + 
//    			" forwarded by " +  aSource + " to " + aDestination ;
		String aMessage = aRemoteEndPoint + "->" + aSendMessage;
		ByteBufferSendInitiated retVal = new ByteBufferSendInitiated(aMessage, aSource, aDestination, aRemoteEndPoint, aSendMessage);
		retVal.announce();
		return retVal;
	}

}
