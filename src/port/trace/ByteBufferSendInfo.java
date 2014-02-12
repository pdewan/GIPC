package port.trace;

import inputport.datacomm.NamingSender;

import java.nio.ByteBuffer;

public class ByteBufferSendInfo extends MessageSendInfo {
	ByteBuffer byteBufferMessage;
	
	public ByteBufferSendInfo(NamingSender aSource, NamingSender aDestination, String aRemoteEndPoint, ByteBuffer aSendMessage) {
		super("ByteBuffer: " + aSendMessage + " with remote end point: " + aRemoteEndPoint + 
    			" forwarded by " +  aSource + " to " + aDestination, aSource, aDestination, aRemoteEndPoint);
		byteBufferMessage = aSendMessage;
	}
	
	public ByteBuffer getByteBufferMessage() {
		return byteBufferMessage;
	}
	
	public static ByteBufferSendInfo newCase(NamingSender aSource, NamingSender aDestination, String aRemoteEndPoint, ByteBuffer aSendMessage) {
//    	String aMessage = "ByteBuffer: " + aSendMessage + " with remote end point: " + aRemoteEndPoint + 
//    			" forwarded by " +  aSource + " to " + aDestination ;
		ByteBufferSendInfo retVal = new ByteBufferSendInfo(aSource, aDestination, aRemoteEndPoint, aSendMessage);
		retVal.announce();
		return retVal;
	}

}
