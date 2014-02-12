package port.trace;

import util.trace.TraceableInfo;

public abstract class ConnectionInfo extends TraceableInfo  {
	Object destination;
	boolean isSent;
	public ConnectionInfo(String aMessage, Object aSource,  Object aDestination, boolean anIsSent) {
		super(aMessage, aSource);
		destination = aDestination;
		isSent = anIsSent;
	}
	public Object getSource() {
		return getFinder();
	}
	
	public Object getDestination() {
		return destination;
	}
	public boolean isSent() {
		return isSent;
	}

}
