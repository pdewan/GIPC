package util.trace.port.buffer;

import inputport.datacomm.ReceiveNotifier;

import java.nio.ByteBuffer;

import util.trace.port.MessageReceiveInfo;

public class TrapperBufferReceived extends MessageReceiveInfo {
	ByteBuffer byteBufferMessage;
	

	public TrapperBufferReceived(ReceiveNotifier aSource, ReceiveNotifier aDestination, String aRemoteEndPoint, ByteBuffer aReceiveMessage) {
		super("ByteBuffer: " + aReceiveMessage + " with remote end point: " + aRemoteEndPoint + 
    			" forwarded by " +  aSource + " to " + aDestination, aSource, aDestination, aRemoteEndPoint);
		byteBufferMessage = aReceiveMessage;
	}
	public TrapperBufferReceived(String aMessage, ReceiveNotifier aSource, ReceiveNotifier aDestination, String aRemoteEndPoint, ByteBuffer aReceiveMessage) {
		super(aMessage, aSource, aDestination, aRemoteEndPoint);
		byteBufferMessage = aReceiveMessage;
	}
	
	public ByteBuffer getByteBufferMessage() {
		return byteBufferMessage;
	}
	public static TrapperBufferReceived newCase(ReceiveNotifier aSource, ReceiveNotifier aDestination, String aRemoteEndPoint, ByteBuffer aReceiveMessage) {
//    	String aMessage = "ByteBuffer: " + aReceiveMessage + " with remote end point: " + aRemoteEndPoint + 
//    			" forwarded by " +  aSource + " to " + aDestination ;
		String aMessage = aRemoteEndPoint + "<-" + aReceiveMessage;
		TrapperBufferReceived retVal =  new TrapperBufferReceived(aSource, aDestination, aRemoteEndPoint, aReceiveMessage);
		retVal.announce();
		return retVal;

	}

}
