package util.trace.port.rpc;

import inputport.rpc.RemoteCall;
import util.annotations.ComponentWidth;
import util.annotations.DisplayToString;
@DisplayToString(true)
@ComponentWidth(1000)
public class ReceivedCallEndedOld extends CallInfo {
	

	public ReceivedCallEndedOld(String aMessage, Object aSource, Object aDestination, String aRemoteEndPoint, RemoteCall aCall) {
		super(aMessage, aSource, aDestination, aRemoteEndPoint, aCall);
	}
	
	
	public static ReceivedCallEndedOld newCase(Object aSource, Object aDestination, String aRemoteEndPoint, RemoteCall aCall) {
    	String aMessage = "Completed call: " + aCall + " with remote end point: " + aRemoteEndPoint + 
    			" forwarded by " +  aSource + " to " + aDestination ;
    	ReceivedCallEndedOld retVal = new ReceivedCallEndedOld(aMessage, aSource, aDestination, aRemoteEndPoint, aCall);
    	retVal.announce();
    	return retVal;

	}
	
	static {
//		Tracer.setKeywordDisplayStatus(ReceivedCallEnded.class, true);
	}

}
