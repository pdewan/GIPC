package port.trace;

import inputport.datacomm.NamingSender;

public class ObjectSendInfo extends MessageSendInfo {
	Object object;
	
	public ObjectSendInfo( NamingSender aSource, NamingSender aDestination, String aRemoteEndPoint, Object aSendMessage) {
		super("Object: " + aSendMessage + " with remote end point: " + aRemoteEndPoint + 
    			" forwarded by " +  aSource + " to " + aDestination, aSource, aDestination, aRemoteEndPoint);
		object = aSendMessage;
	}
	
	public Object getObjectMessage() {
		return object;
	}
	public static ObjectSendInfo newCase(NamingSender aSource, NamingSender aDestination, String aRemoteEndPoint, Object aSendMessage) {
//    	String aMessage = "Object: " + aSendMessage + " with remote end point: " + aRemoteEndPoint + 
//    			" forwarded by " +  aSource + " to " + aDestination ;
		ObjectSendInfo retVal = new ObjectSendInfo(aSource, aDestination, aRemoteEndPoint, aSendMessage);
		retVal.announce();
		return retVal;

	}

}
