package port.trace;

import util.trace.TraceableInfo;
import inputport.datacomm.NamingSender;

public class ClientNameSent extends TraceableInfo {
	Object object;
	
	
	public ClientNameSent(String aMessage, Object aSource, String aRemoteEndPoint, String aSendMessage) {
		super(aMessage, aSource);;
		object = aSendMessage;
	}
	
	public Object getObjectMessage() {
		return object;
	}
	public static ClientNameSent newCase(Object aSource,  String aRemoteEndPoint, String aSendMessage) {
//    	String aMessage = "Object: " + aSendMessage + " with remote end point: " + aRemoteEndPoint + 
//    			" forwarded by " +  aSource + " to " + aDestination ;
		String aMessage = aRemoteEndPoint + "->" + aSendMessage;
		ClientNameSent retVal = new ClientNameSent(aMessage, aSource,  aRemoteEndPoint, aSendMessage);
		retVal.announce();
		return retVal;

	}

}
