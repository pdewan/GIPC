package port.trace;

import util.annotations.ComponentWidth;
import util.annotations.DisplayToString;
import util.trace.Tracer;
import inputport.datacomm.ReceiveNotifier;
import inputport.rpc.RemoteCall;
@DisplayToString(true)
@ComponentWidth(1000)
public class ReceivedCallEnded extends CallInfo {
	

	public ReceivedCallEnded(String aMessage, Object aSource, Object aDestination, String aRemoteEndPoint, RemoteCall aCall) {
		super(aMessage, aSource, aDestination, aRemoteEndPoint, aCall);
	}
	
	
	public static ReceivedCallEnded newCase(Object aSource, Object aDestination, String aRemoteEndPoint, RemoteCall aCall) {
    	String aMessage = "Completed call: " + aCall + " with remote end point: " + aRemoteEndPoint + 
    			" forwarded by " +  aSource + " to " + aDestination ;
    	ReceivedCallEnded retVal = new ReceivedCallEnded(aMessage, aSource, aDestination, aRemoteEndPoint, aCall);
    	retVal.announce();
    	return retVal;

	}
	
	static {
//		Tracer.setKeywordDisplayStatus(ReceivedCallEnded.class, true);
	}

}
