package port.trace.serialization.extensible;

import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;

import inputport.ConnectionManager;
import inputport.rpc.RemoteCall;
import port.trace.rpc.ReceivedCallEndedOld;
import util.trace.TraceableInfo;

public class ExtensibleBufferDeserializationFinished extends TraceableInfo {
	public ExtensibleBufferDeserializationFinished(String aMessage,
			Object aValueSerializer, ByteBuffer anInputBuffer,
			Object anOutputObject,
			Object aRetrievedObjects) {
		super(aMessage, aValueSerializer);
	}

	public static ExtensibleBufferDeserializationFinished newCase(
			Object aValueSerializer, String aSource, ByteBuffer anInputBuffer,
			Object anOutputObject,
			Object aRetrievedObjects) {
		String aMessage = anInputBuffer + "->" + anOutputObject + "(" + aRetrievedObjects + ")";
		ExtensibleBufferDeserializationFinished retVal = new ExtensibleBufferDeserializationFinished(
				aMessage, aValueSerializer, anInputBuffer, anOutputObject, aRetrievedObjects);
		retVal.announce();
		return retVal;
	}
}
