package port.trace;

import inputport.datacomm.ReceiveNotifier;

import java.nio.ByteBuffer;

public class ByteBufferReceived extends MessageReceiveInfo {
	ByteBuffer byteBufferMessage;
	

	public ByteBufferReceived(ReceiveNotifier aSource, ReceiveNotifier aDestination, String aRemoteEndPoint, ByteBuffer aReceiveMessage) {
		super("ByteBuffer: " + aReceiveMessage + " with remote end point: " + aRemoteEndPoint + 
    			" forwarded by " +  aSource + " to " + aDestination, aSource, aDestination, aRemoteEndPoint);
		byteBufferMessage = aReceiveMessage;
	}
	public ByteBufferReceived(String aMessage, ReceiveNotifier aSource, ReceiveNotifier aDestination, String aRemoteEndPoint, ByteBuffer aReceiveMessage) {
		super(aMessage, aSource, aDestination, aRemoteEndPoint);
		byteBufferMessage = aReceiveMessage;
	}
	
	public ByteBuffer getByteBufferMessage() {
		return byteBufferMessage;
	}
	public static ByteBufferReceived newCase(ReceiveNotifier aSource, ReceiveNotifier aDestination, String aRemoteEndPoint, ByteBuffer aReceiveMessage) {
//    	String aMessage = "ByteBuffer: " + aReceiveMessage + " with remote end point: " + aRemoteEndPoint + 
//    			" forwarded by " +  aSource + " to " + aDestination ;
		String aMessage = aRemoteEndPoint + "<-" + aReceiveMessage;
		ByteBufferReceived retVal =  new ByteBufferReceived(aSource, aDestination, aRemoteEndPoint, aReceiveMessage);
		retVal.announce();
		return retVal;

	}

}
