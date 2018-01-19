package util.trace.factories;

import java.net.SocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Arrays;

import inputport.nio.manager.listeners.SocketChannelAcceptListener;
import inputport.nio.manager.listeners.SocketChannelReadListener;
import inputport.rpc.RemoteCall;
import util.trace.TraceableInfo;
import util.trace.port.rpc.ReceivedCallEndedOld;

public class SelectorFactorySet extends TraceableInfo {	
	public SelectorFactorySet(String aMessage, Object aFinder, 
			Object aFactory) {
		super(aMessage, aFinder);
	}
	public static SelectorFactorySet newCase(Object aSource, 			
			Object aFactory) {    	
		String aMessage = 
				aFactory.toString();
		SelectorFactorySet retVal = new SelectorFactorySet(aMessage, 
				aSource, 
				aFactory);
    	retVal.announce();
    	return retVal;
	}
//	static {
//		FactoryTraceUtility.setTracing();
//	}
}
