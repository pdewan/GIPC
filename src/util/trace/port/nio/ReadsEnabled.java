package util.trace.port.nio;

import java.nio.channels.SocketChannel;

public class ReadsEnabled extends SocketChannelInfo {	
	public ReadsEnabled(String aMessage, Object aFinder, 
			SocketChannel aSocketChannel) {
		super(aMessage, aFinder, aSocketChannel);
	}
	public static ReadsEnabled newCase(Object aSource, 			
			SocketChannel aSocketChannel) {    	
		String aMessage = 
				aSocketChannel.toString();
		ReadsEnabled retVal = new ReadsEnabled(aMessage, 
				aSource, 
				aSocketChannel);
    	retVal.announce();
    	return retVal;
	}
}
