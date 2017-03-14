package port.trace.objects;

import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;

import inputport.ConnectionManager;
import inputport.rpc.RemoteCall;
import port.trace.rpc.ReceivedCallEndedOld;
import util.trace.TraceableInfo;

public class ObjectSerializationFinished extends TraceableInfo {	
	public ObjectSerializationFinished(String aMessage, Object aFinder,
			String aDestination,
			ByteBuffer aByteBuffer,
			Object anObject) {
		super(aMessage, aFinder);
	}
	public static ObjectSerializationFinished newCase(Object aFinder, 
			String aDestination,
			ByteBuffer aByteBuffer, Object anObject) {    	
		String aMessage =
				aDestination + "->" +
				anObject + "->" +
				aByteBuffer ;
		ObjectSerializationFinished retVal = new ObjectSerializationFinished(aMessage, 
				aFinder,
				aDestination,  aByteBuffer, anObject);
    	retVal.announce();
    	return retVal;
	}
}
