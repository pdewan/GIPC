package port.trace.serialization.extensible;

import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;

import inputport.ConnectionManager;
import inputport.rpc.RemoteCall;
import port.trace.rpc.ReceivedCallEndedOld;
import util.trace.TraceableInfo;

public class ExtensibleValueSerializationFinished extends TraceableInfo {
	public ExtensibleValueSerializationFinished(String aMessage,
			Object aValueSerializer, Object anObject, ByteBuffer anOutputBuffer, Object aVisitedObjects) {
		super(aMessage, aValueSerializer);
	}

	public static ExtensibleValueSerializationFinished newCase(Object aValueSerializer,
			Object anObject, ByteBuffer anOutputBuffer, Object aVisitedObjects) {
		String aMessage = anOutputBuffer + "<--" + anObject + "(" + aVisitedObjects + ")";;
		ExtensibleValueSerializationFinished retVal = new ExtensibleValueSerializationFinished(
				aMessage, aValueSerializer, anObject, anOutputBuffer, aVisitedObjects);
		retVal.announce();
		return retVal;
		
	}
}
