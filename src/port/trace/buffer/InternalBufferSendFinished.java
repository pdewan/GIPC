package port.trace.buffer;

import inputport.datacomm.NamingSender;

import java.nio.ByteBuffer;

import port.trace.MessageSendInfo;

public class InternalBufferSendFinished extends MessageSendInfo {
	ByteBuffer byteBufferMessage;
	
	public InternalBufferSendFinished(NamingSender aSource, NamingSender aDestination, String aRemoteEndPoint, ByteBuffer aSendMessage) {
		super("ByteBuffer: " + aSendMessage + " with remote end point: " + aRemoteEndPoint + 
    			" forwarded by " +  aSource + " to " + aDestination, aSource, aDestination, aRemoteEndPoint);
		byteBufferMessage = aSendMessage;
	}
	public InternalBufferSendFinished(String aMessage, NamingSender aSource, NamingSender aDestination, String aRemoteEndPoint, ByteBuffer aSendMessage) {
		super (aMessage, aSource, aDestination, aRemoteEndPoint );
		byteBufferMessage = aSendMessage;
	}
	
	public ByteBuffer getByteBufferMessage() {
		return byteBufferMessage;
	}
	
	public static InternalBufferSendFinished newCase(NamingSender aSource, NamingSender aDestination, String aRemoteEndPoint, ByteBuffer aSendMessage) {
//    	String aMessage = "ByteBuffer: " + aSendMessage + " with remote end point: " + aRemoteEndPoint + 
//    			" forwarded by " +  aSource + " to " + aDestination ;
		String aMessage = aRemoteEndPoint + "->" + aSendMessage;
		InternalBufferSendFinished retVal = new InternalBufferSendFinished(aMessage, aSource, aDestination, aRemoteEndPoint, aSendMessage);
		retVal.announce();
		return retVal;
	}

}
