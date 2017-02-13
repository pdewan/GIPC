package port.trace;

import inputport.datacomm.ReceiveNotifier;

public class ObjectReceived extends MessageReceiveInfo {
	Object objectMessage;
	

	public ObjectReceived(ReceiveNotifier aSource, ReceiveNotifier aDestination, String aRemoteEndPoint, Object aReceiveMessage) {
		super("Object: " + aReceiveMessage + " with remote end point: " + aRemoteEndPoint + 
    			" forwarded by " +  aSource + " to " + aDestination, aSource, aDestination, aRemoteEndPoint);
		objectMessage = aReceiveMessage;
	}
	public ObjectReceived(String aMessage, ReceiveNotifier aSource, ReceiveNotifier aDestination, String aRemoteEndPoint, Object aReceiveMessage) {
		super(aMessage, aSource, aDestination, aRemoteEndPoint);
		objectMessage = aReceiveMessage;
	}
	
	public Object getObjectMessage() {
		return objectMessage;
	}
	public static ObjectReceived newCase(ReceiveNotifier aSource, ReceiveNotifier aDestination, String aRemoteEndPoint, Object aReceiveMessage) {
//    	String aMessage = "Object: " + aReceiveMessage + " with remote end point: " + aRemoteEndPoint + 
//    			" forwarded by " +  aSource + " to " + aDestination ;
    	String aMessage =  aRemoteEndPoint + "<-" + aReceiveMessage;
    	ObjectReceived retVal = new ObjectReceived(aMessage, aSource, aDestination, aRemoteEndPoint, aReceiveMessage);
    	retVal.announce();
    	return retVal;
	}

}
