package trace.port;


public class AConnectionEvent extends ConnectionInfo implements ConnectionEvent {
//	Object destination, source;	
//	boolean isSent;
	public AConnectionEvent (Object aSource,  Object aDestination, boolean anIsSent) {
		super(computeConnectionEventMessage(aSource, aDestination, anIsSent), aSource, aDestination, anIsSent);
		this.announce(); // need a factory
//		isForwarded = aIsForwarded;
	}
	public static String isSentToString(boolean anIsSent) {
		return anIsSent?"sending":"receiving";
	}
	public static String computeConnectionEventMessage (Object aSource,  Object aDestination, boolean anIsSent) {
		
			return "Connection of " + isSentToString(anIsSent) + " " + aSource + " to " + aDestination;
				
	
	}
//	@Override
//	public Object getSource() {
//		return source;
//	}
//	@Override
//	public Object getDestination() {
//		return destination;
//	}
//	@Override
//	public boolean isSent() {
//		return isSent;
//	}
//	@Override
//	public boolean isForwarded() {
//		return isForwarded;
//	}

}
