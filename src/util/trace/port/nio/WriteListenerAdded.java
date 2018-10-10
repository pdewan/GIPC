package util.trace.port.nio;

import java.nio.channels.SocketChannel;

import inputport.nio.manager.listeners.WriteBoundedBufferListener;

public class WriteListenerAdded extends SocketChannelInfo {	
	public WriteListenerAdded(String aMessage, Object aFinder, 
			SocketChannel aSocketChannel, 
			WriteBoundedBufferListener aListener) {
		super(aMessage, aFinder, aSocketChannel);
	}
	public static WriteListenerAdded newCase(Object aSource, 			
			SocketChannel aSocketChannel, WriteBoundedBufferListener aListener) {    	
		String aMessage = 
				aListener.toString() + "<-" + aSocketChannel;
		WriteListenerAdded retVal = new WriteListenerAdded(aMessage, 
				aSource, 
				aSocketChannel,
				aListener);
    	retVal.announce();
    	return retVal;
	}
}
