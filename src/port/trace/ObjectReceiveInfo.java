package port.trace;

import inputport.datacomm.ReceiveNotifier;

public class ObjectReceiveInfo extends MessageReceiveInfo {
	Object objectMessage;
	

	public ObjectReceiveInfo(ReceiveNotifier aSource, ReceiveNotifier aDestination, String aRemoteEndPoint, Object aReceiveMessage) {
		super("Object: " + aReceiveMessage + " with remote end point: " + aRemoteEndPoint + 
    			" forwarded by " +  aSource + " to " + aDestination, aSource, aDestination, aRemoteEndPoint);
		objectMessage = aReceiveMessage;
	}
	
	public Object getObjectMessage() {
		return objectMessage;
	}
	public static ObjectReceiveInfo newCase(ReceiveNotifier aSource, ReceiveNotifier aDestination, String aRemoteEndPoint, Object aReceiveMessage) {
    	String aMessage = "Object: " + aReceiveMessage + " with remote end point: " + aRemoteEndPoint + 
    			" forwarded by " +  aSource + " to " + aDestination ;
    	ObjectReceiveInfo retVal = new ObjectReceiveInfo(aSource, aDestination, aRemoteEndPoint, aReceiveMessage);
    	retVal.announce();
    	return retVal;
	}

}
