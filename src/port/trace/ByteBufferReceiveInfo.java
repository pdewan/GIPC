package port.trace;

import inputport.datacomm.ReceiveNotifier;

import java.nio.ByteBuffer;

public class ByteBufferReceiveInfo extends MessageReceiveInfo {
	ByteBuffer byteBufferMessage;
	

	public ByteBufferReceiveInfo(ReceiveNotifier aSource, ReceiveNotifier aDestination, String aRemoteEndPoint, ByteBuffer aReceiveMessage) {
		super("ByteBuffer: " + aReceiveMessage + " with remote end point: " + aRemoteEndPoint + 
    			" forwarded by " +  aSource + " to " + aDestination, aSource, aDestination, aRemoteEndPoint);
		byteBufferMessage = aReceiveMessage;
	}
	
	public ByteBuffer getByteBufferMessage() {
		return byteBufferMessage;
	}
	public static ByteBufferReceiveInfo newCase(ReceiveNotifier aSource, ReceiveNotifier aDestination, String aRemoteEndPoint, ByteBuffer aReceiveMessage) {
//    	String aMessage = "ByteBuffer: " + aReceiveMessage + " with remote end point: " + aRemoteEndPoint + 
//    			" forwarded by " +  aSource + " to " + aDestination ;
		ByteBufferReceiveInfo retVal =  new ByteBufferReceiveInfo(aSource, aDestination, aRemoteEndPoint, aReceiveMessage);
		retVal.announce();
		return retVal;

	}

}
