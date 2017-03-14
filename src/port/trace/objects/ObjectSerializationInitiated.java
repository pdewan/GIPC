package port.trace.objects;

import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;

import inputport.ConnectionManager;
import inputport.rpc.RemoteCall;
import port.trace.rpc.ReceivedCallEndedOld;
import util.trace.TraceableInfo;

public class ObjectSerializationInitiated extends TraceableInfo {	
	public ObjectSerializationInitiated(String aMessage, Object aFinder,
			String aDestination,
			Object anObject,
			Object aDelegate) {
		super(aMessage, aFinder);
	}
	public static ObjectSerializationInitiated newCase(Object aFinder, 
			String aDestination,
			Object anObject,
			Object aDelegate) {    	
		String aMessage =
				aDestination + "<-" +
				
				anObject +
				"( via " + aDelegate + ")";
		ObjectSerializationInitiated retVal = new ObjectSerializationInitiated(aMessage, 
				aFinder,
				aDestination,  anObject, aDelegate);
    	retVal.announce();
    	return retVal;
	}
}
