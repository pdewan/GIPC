package port.trace;

import inputport.datacomm.NamingSender;

public class ObjectSendInitiated extends MessageSendInfo {
	Object object;
	
	public ObjectSendInitiated( NamingSender aSource, NamingSender aDestination, String aRemoteEndPoint, Object aSendMessage) {
		super("Object: " + aSendMessage + " with remote end point: " + aRemoteEndPoint + 
    			" forwarded by " +  aSource + " to " + aDestination, aSource, aDestination, aRemoteEndPoint);
		object = aSendMessage;
	}
	public ObjectSendInitiated(String aMessage, NamingSender aSource, NamingSender aDestination, String aRemoteEndPoint, Object aSendMessage) {
		super(aMessage, aSource, aDestination, aRemoteEndPoint);
		object = aSendMessage;
	}
	
	public Object getObjectMessage() {
		return object;
	}
	public static ObjectSendInitiated newCase(NamingSender aSource, NamingSender aDestination, String aRemoteEndPoint, Object aSendMessage) {
//    	String aMessage = "Object: " + aSendMessage + " with remote end point: " + aRemoteEndPoint + 
//    			" forwarded by " +  aSource + " to " + aDestination ;
		String aMessage = aRemoteEndPoint + "->" + aSendMessage;
		ObjectSendInitiated retVal = new ObjectSendInitiated(aMessage, aSource, aDestination, aRemoteEndPoint, aSendMessage);
		retVal.announce();
		return retVal;

	}

}
