package util.trace.port;

import util.annotations.DisplayToString;
import util.trace.TraceableInfo;
@DisplayToString(true)
public  class JoinCallInitiated  extends TraceableInfo {
	String remoteEndPoint;
	Object destination;
	Object data;
	public JoinCallInitiated(String aMessage, Object aFinder)  {
		super(aMessage, aFinder );
		
	}
	
//	
//	public Object getDestination() {
//		return  getFinder();
//	}
//	
	
	public String getRemoteEndPoint() {
		return remoteEndPoint;
	}
	
	public static JoinCallInitiated newCase(Object aFinder) {
    	String aMessage = "Join call made";
    	JoinCallInitiated retVal = new JoinCallInitiated(aMessage, aFinder);
    	retVal.announce();
    	return retVal;
	}
	static {
//		Tracer.setKeywordDisplayStatus(JoinCallInitiated.class, true);
	}

}
