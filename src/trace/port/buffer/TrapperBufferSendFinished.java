package trace.port.buffer;

import inputport.datacomm.NamingSender;

import java.nio.ByteBuffer;

import trace.port.MessageSendInfo;

public class TrapperBufferSendFinished extends MessageSendInfo {
	ByteBuffer byteBufferMessage;
	
	public TrapperBufferSendFinished(NamingSender aSource, NamingSender aDestination, String aRemoteEndPoint, ByteBuffer aSendMessage) {
		super("ByteBuffer: " + aSendMessage + " with remote end point: " + aRemoteEndPoint + 
    			" forwarded by " +  aSource + " to " + aDestination, aSource, aDestination, aRemoteEndPoint);
		byteBufferMessage = aSendMessage;
	}
	public TrapperBufferSendFinished(String aMessage, NamingSender aSource, NamingSender aDestination, String aRemoteEndPoint, ByteBuffer aSendMessage) {
		super (aMessage, aSource, aDestination, aRemoteEndPoint );
		byteBufferMessage = aSendMessage;
	}
	
	public ByteBuffer getByteBufferMessage() {
		return byteBufferMessage;
	}
	
	public static TrapperBufferSendFinished newCase(NamingSender aSource, NamingSender aDestination, String aRemoteEndPoint, ByteBuffer aSendMessage) {
//    	String aMessage = "ByteBuffer: " + aSendMessage + " with remote end point: " + aRemoteEndPoint + 
//    			" forwarded by " +  aSource + " to " + aDestination ;
		String aMessage = aRemoteEndPoint + "->" + aSendMessage;
		TrapperBufferSendFinished retVal = new TrapperBufferSendFinished(aMessage, aSource, aDestination, aRemoteEndPoint, aSendMessage);
		retVal.announce();
		return retVal;
	}

}
