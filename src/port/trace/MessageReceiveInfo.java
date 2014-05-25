package port.trace;

import util.trace.TraceableInfo;

public abstract class MessageReceiveInfo  extends TraceableInfo {
	String remoteEndPoint;
	Object destination;
	Object data;
	public MessageReceiveInfo(String aMessage, Object aSource, Object aDestination, String aRemoteEndPoint)  {
		super(aMessage, aSource );
		remoteEndPoint = aRemoteEndPoint;
		destination = aDestination;
	}
	
//	
//	public Object getDestination() {
//		return  getFinder();
//	}
//	
	
	public String getRemoteEndPoint() {
		return remoteEndPoint;
	}

}
