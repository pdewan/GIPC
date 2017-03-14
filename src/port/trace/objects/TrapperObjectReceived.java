package port.trace.objects;

import port.trace.MessageReceiveInfo;
import inputport.datacomm.ReceiveNotifier;

public class TrapperObjectReceived extends MessageReceiveInfo {
	Object objectMessage;
	

	public TrapperObjectReceived(ReceiveNotifier aSource, ReceiveNotifier aDestination, String aRemoteEndPoint, Object aReceiveMessage) {
		super("Object: " + aReceiveMessage + " with remote end point: " + aRemoteEndPoint + 
    			" forwarded by " +  aSource + " to " + aDestination, aSource, aDestination, aRemoteEndPoint);
		objectMessage = aReceiveMessage;
	}
	public TrapperObjectReceived(String aMessage, ReceiveNotifier aSource, ReceiveNotifier aDestination, String aRemoteEndPoint, Object aReceiveMessage) {
		super(aMessage, aSource, aDestination, aRemoteEndPoint);
		objectMessage = aReceiveMessage;
	}
	
	public Object getObjectMessage() {
		return objectMessage;
	}
	public static TrapperObjectReceived newCase(ReceiveNotifier aSource, ReceiveNotifier aDestination, String aRemoteEndPoint, Object aReceiveMessage) {
//    	String aMessage = "Object: " + aReceiveMessage + " with remote end point: " + aRemoteEndPoint + 
//    			" forwarded by " +  aSource + " to " + aDestination ;
    	String aMessage =  aRemoteEndPoint + "<-" + aReceiveMessage;
    	TrapperObjectReceived retVal = new TrapperObjectReceived(aMessage, aSource, aDestination, aRemoteEndPoint, aReceiveMessage);
    	retVal.announce();
    	return retVal;
	}

}
