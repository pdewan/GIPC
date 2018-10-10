package util.trace.port.nio;

import java.nio.channels.SocketChannel;

import inputport.nio.manager.listeners.SocketChannelReadListener;

public class ReadListenerAdded extends SocketChannelInfo {	
	public ReadListenerAdded(String aMessage, Object aFinder, 
			SocketChannel aSocketChannel, 
			SocketChannelReadListener aListener) {
		super(aMessage, aFinder, aSocketChannel);
	}
	public static ReadListenerAdded newCase(Object aSource, 			
			SocketChannel aSocketChannel, SocketChannelReadListener aListener) {    	
		String aMessage = 
				aListener.toString() + "<-" + aSocketChannel;
		ReadListenerAdded retVal = new ReadListenerAdded(aMessage, 
				aSource, 
				aSocketChannel,
				aListener);
    	retVal.announce();
    	return retVal;
	}
}
