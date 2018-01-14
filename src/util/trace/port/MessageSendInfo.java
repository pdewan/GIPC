package util.trace.port;

import inputport.datacomm.NamingSender;
import util.trace.TraceableInfo;

public abstract class MessageSendInfo  extends TraceableInfo {
	String remoteEndPoint;
	NamingSender destination;
	public MessageSendInfo(String aMessage, NamingSender aSource, NamingSender aDestination, String aRemoteEndPoint) {
		super(aMessage, aSource );
		remoteEndPoint = aRemoteEndPoint;
		destination = aDestination;
	}
	
//	public NamingSender getSource() {
//		return (NamingSender) getFinder();
//	}
	
	public NamingSender getDestination() {
		return destination;
	}
	
	public String getRemoteEndPoint() {
		return remoteEndPoint;
	}

}
