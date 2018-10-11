package util.trace.port.nio;

import java.nio.channels.SocketChannel;
import java.util.List;

import inputport.nio.manager.listeners.WriteBoundedBufferListener;
import util.trace.TraceableInfo;

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
