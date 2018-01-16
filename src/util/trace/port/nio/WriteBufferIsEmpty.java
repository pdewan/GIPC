package util.trace.port.nio;

import java.net.SocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;
import java.util.List;

import inputport.nio.manager.WriteBoundedBufferListener;
import inputport.rpc.RemoteCall;
import util.trace.TraceableInfo;
import util.trace.port.rpc.ReceivedCallEndedOld;

public class WriteBufferIsEmpty extends TraceableInfo {	
	public WriteBufferIsEmpty(String aMessage, Object aFinder,
			SocketChannel aSocketChannel, List<WriteBoundedBufferListener> aListeners) {
		super(aMessage, aFinder);
	}
	public static WriteBufferIsEmpty newCase(Object aSource, 			
			SocketChannel aSocketChannel, List<WriteBoundedBufferListener> aListeners) {    	
		String aMessage = 
				aListeners + "<--" + aSocketChannel.toString()  ;
		WriteBufferIsEmpty retVal = new WriteBufferIsEmpty(aMessage, 
				aSource, 
				aSocketChannel, aListeners );
    	retVal.announce();
    	return retVal;
	}
}
