package trace.port.buffer;

import util.trace.TraceableInfo;
import inputport.datacomm.NamingSender;

public class ClientNameSendInitiated extends TraceableInfo {
	Object object;
	
	
	public ClientNameSendInitiated(String aMessage, Object aSource, String aRemoteEndPoint, String aSendMessage) {
		super(aMessage, aSource);;
		object = aSendMessage;
	}
	
	public Object getObjectMessage() {
		return object;
	}
	public static ClientNameSendInitiated newCase(Object aSource,  String aRemoteEndPoint, String aSendMessage) {
//    	String aMessage = "Object: " + aSendMessage + " with remote end point: " + aRemoteEndPoint + 
//    			" forwarded by " +  aSource + " to " + aDestination ;
		String aMessage = aRemoteEndPoint + "->" + aSendMessage;
		ClientNameSendInitiated retVal = new ClientNameSendInitiated(aMessage, aSource,  aRemoteEndPoint, aSendMessage);
		retVal.announce();
		return retVal;

	}

}
