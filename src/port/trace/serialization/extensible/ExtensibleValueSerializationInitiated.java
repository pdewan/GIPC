package port.trace.serialization.extensible;

import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;

import inputport.ConnectionManager;
import inputport.rpc.RemoteCall;
import port.trace.rpc.ReceivedCallEndedOld;
import util.trace.TraceableInfo;

public class ExtensibleValueSerializationInitiated extends TraceableInfo {
	public ExtensibleValueSerializationInitiated(String aMessage,
			Object aValueSerializer, Object anObject, Object anInputBuffer) {
		super(aMessage, aValueSerializer);
	}

	public static ExtensibleValueSerializationInitiated newCase(Object aValueSerializer,
			Object anObject, Object anInputBuffer) {
		String aMessage = anInputBuffer + "<--" + anObject;
		ExtensibleValueSerializationInitiated retVal = new ExtensibleValueSerializationInitiated(
				aMessage, aValueSerializer, anObject, anInputBuffer);
		retVal.announce();
		return retVal;		
	}
}
