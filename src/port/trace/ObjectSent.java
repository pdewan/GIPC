package port.trace;

import inputport.datacomm.NamingSender;

public class ObjectSent extends MessageSendInfo {
	Object object;
	
	public ObjectSent( NamingSender aSource, NamingSender aDestination, String aRemoteEndPoint, Object aSendMessage) {
		super("Object: " + aSendMessage + " with remote end point: " + aRemoteEndPoint + 
    			" forwarded by " +  aSource + " to " + aDestination, aSource, aDestination, aRemoteEndPoint);
		object = aSendMessage;
	}
	public ObjectSent(String aMessage, NamingSender aSource, NamingSender aDestination, String aRemoteEndPoint, Object aSendMessage) {
		super(aMessage, aSource, aDestination, aRemoteEndPoint);
		object = aSendMessage;
	}
	
	public Object getObjectMessage() {
		return object;
	}
	public static ObjectSent newCase(NamingSender aSource, NamingSender aDestination, String aRemoteEndPoint, Object aSendMessage) {
//    	String aMessage = "Object: " + aSendMessage + " with remote end point: " + aRemoteEndPoint + 
//    			" forwarded by " +  aSource + " to " + aDestination ;
		String aMessage = aRemoteEndPoint + "->" + aSendMessage;
		ObjectSent retVal = new ObjectSent(aMessage, aSource, aDestination, aRemoteEndPoint, aSendMessage);
		retVal.announce();
		return retVal;

	}

}
