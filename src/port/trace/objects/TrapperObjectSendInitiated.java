package port.trace.objects;

import port.trace.MessageSendInfo;
import inputport.datacomm.NamingSender;

public class TrapperObjectSendInitiated extends MessageSendInfo {
	Object object;
	
	public TrapperObjectSendInitiated( NamingSender aSource, NamingSender aDestination, String aRemoteEndPoint, Object aSendMessage) {
		super("Object: " + aSendMessage + " with remote end point: " + aRemoteEndPoint + 
    			" forwarded by " +  aSource + " to " + aDestination, aSource, aDestination, aRemoteEndPoint);
		object = aSendMessage;
	}
	public TrapperObjectSendInitiated(String aMessage, NamingSender aSource, NamingSender aDestination, String aRemoteEndPoint, Object aSendMessage) {
		super(aMessage, aSource, aDestination, aRemoteEndPoint);
		object = aSendMessage;
	}
	
	public Object getObjectMessage() {
		return object;
	}
	public static TrapperObjectSendInitiated newCase(NamingSender aSource, NamingSender aDestination, String aRemoteEndPoint, Object aSendMessage) {
//    	String aMessage = "Object: " + aSendMessage + " with remote end point: " + aRemoteEndPoint + 
//    			" forwarded by " +  aSource + " to " + aDestination ;
		String aMessage = aRemoteEndPoint + "->" + aSendMessage;
		TrapperObjectSendInitiated retVal = new TrapperObjectSendInitiated(aMessage, aSource, aDestination, aRemoteEndPoint, aSendMessage);
		retVal.announce();
		return retVal;

	}

}
