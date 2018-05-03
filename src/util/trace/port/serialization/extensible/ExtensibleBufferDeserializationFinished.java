package util.trace.port.serialization.extensible;

import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;

import inputport.ConnectionManager;
import inputport.rpc.RemoteCall;
import util.trace.TraceableInfo;
import util.trace.port.rpc.ReceivedCallEndedOld;

public class ExtensibleBufferDeserializationFinished extends TraceableInfo {
	public ExtensibleBufferDeserializationFinished(String aMessage,
			Object aValueSerializer, Object anInputBuffer,
			Object anOutputObject,
			Object aRetrievedObjects) {
		super(aMessage, aValueSerializer);
	}

	public static ExtensibleBufferDeserializationFinished newCase(
			Object aValueSerializer, String aSource, Object anInputBuffer,
			Object anOutputObject,
			Object aRetrievedObjects) {
		String aMessage = 
//				aValueSerializer + ":" + 
				anInputBuffer + "->" + anOutputObject + "(" + aRetrievedObjects + ")";
		ExtensibleBufferDeserializationFinished retVal = new ExtensibleBufferDeserializationFinished(
				aMessage, aValueSerializer, anInputBuffer, anOutputObject, aRetrievedObjects);
		retVal.announce();
		return retVal;
	}
}
