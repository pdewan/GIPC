package util.trace.port.buffer;

import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;

import inputport.ConnectionManager;
import inputport.rpc.RemoteCall;
import util.trace.TraceableInfo;
import util.trace.port.rpc.ReceivedCallEndedOld;

public class NumberBytesSent extends TraceableInfo {	
	public NumberBytesSent(String aMessage, 
			Object aFinder,
			String aSource,
			String aDestination,
			long aNumber
			) {
		super(aMessage, aFinder);
	}
	public static NumberBytesSent newCase(Object aFinder, 
			String aSource,
			String aDestination,
			long aNumber
			) {    	
		String aMessage =
				aDestination + "->" + aSource + " " +
				
				aNumber + " bytes ";
		NumberBytesSent retVal = new NumberBytesSent(aMessage, 
				aFinder,
				aSource, aDestination, aNumber);
    	retVal.announce();
    	return retVal;
	}
}
