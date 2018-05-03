package util.trace.port.serialization.extensible;

import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;

import inputport.ConnectionManager;
import inputport.rpc.RemoteCall;
import util.trace.TraceableInfo;
import util.trace.port.rpc.ReceivedCallEndedOld;

public class ExtensibleValueSerializationFinished extends TraceableInfo {
	public ExtensibleValueSerializationFinished(String aMessage,
			Object aValueSerializer, Object anObject, Object anOutputBuffer, Object aVisitedObjects) {
		super(aMessage, aValueSerializer);
	}

	public static ExtensibleValueSerializationFinished newCase(Object aValueSerializer,
			Object anObject, Object anOutputBuffer, Object aVisitedObjects) {
		String aMessage = 
//				aValueSerializer + ":" + 
		anOutputBuffer + "<--" + anObject + "(" + aVisitedObjects + ")";;
		ExtensibleValueSerializationFinished retVal = new ExtensibleValueSerializationFinished(
				aMessage, aValueSerializer, anObject, anOutputBuffer, aVisitedObjects);
		retVal.announce();
		return retVal;
		
	}
}
