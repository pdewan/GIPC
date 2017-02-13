package port.trace;

import inputport.datacomm.NamingSender;

import java.nio.ByteBuffer;

public class ByteBufferSent extends MessageSendInfo {
	ByteBuffer byteBufferMessage;
	
	public ByteBufferSent(NamingSender aSource, NamingSender aDestination, String aRemoteEndPoint, ByteBuffer aSendMessage) {
		super("ByteBuffer: " + aSendMessage + " with remote end point: " + aRemoteEndPoint + 
    			" forwarded by " +  aSource + " to " + aDestination, aSource, aDestination, aRemoteEndPoint);
		byteBufferMessage = aSendMessage;
	}
	public ByteBufferSent(String aMessage, NamingSender aSource, NamingSender aDestination, String aRemoteEndPoint, ByteBuffer aSendMessage) {
		super (aMessage, aSource, aDestination, aRemoteEndPoint );
		byteBufferMessage = aSendMessage;
	}
	
	public ByteBuffer getByteBufferMessage() {
		return byteBufferMessage;
	}
	
	public static ByteBufferSent newCase(NamingSender aSource, NamingSender aDestination, String aRemoteEndPoint, ByteBuffer aSendMessage) {
//    	String aMessage = "ByteBuffer: " + aSendMessage + " with remote end point: " + aRemoteEndPoint + 
//    			" forwarded by " +  aSource + " to " + aDestination ;
		String aMessage = aRemoteEndPoint + "->" + aSendMessage;
		ByteBufferSent retVal = new ByteBufferSent(aMessage, aSource, aDestination, aRemoteEndPoint, aSendMessage);
		retVal.announce();
		return retVal;
	}

}
