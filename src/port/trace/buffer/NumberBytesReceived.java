package port.trace.buffer;

import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;

import inputport.ConnectionManager;
import inputport.rpc.RemoteCall;
import port.trace.rpc.ReceivedCallEndedOld;
import util.trace.TraceableInfo;

public class NumberBytesReceived extends TraceableInfo {	
	public NumberBytesReceived(String aMessage, 
			Object aFinder,
			String aSource,
			String aDestination,
			int aNumber
			) {
		super(aMessage, aFinder);
	}
	public static NumberBytesReceived newCase(Object aFinder, 
			String aSource,
			String aDestination,
			int aNumber
			) {    	
		String aMessage =
				aSource + "->" + aDestination + " " +
				
				aNumber + " bytes ";
		NumberBytesReceived retVal = new NumberBytesReceived(aMessage, 
				aFinder,
				aSource, aDestination, aNumber);
    	retVal.announce();
    	return retVal;
	}
}
