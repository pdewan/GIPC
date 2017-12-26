package trace.port;


public class AReplaceConnectionEvent extends ConnectionInfo  implements ReplaceConnectionEvent{
//	Object oldDestination, newDestination, source;	
//	boolean isSent;
	boolean deleteOldDestinaton;
	Object oldDestination;
	public AReplaceConnectionEvent (Object aSource,  Object anOldDestination,  Object aNewDestination, boolean anIsSent, boolean aDeleteOldDestination) {
		super (computeReplaceConnectionEventMessage(aSource, anOldDestination, aNewDestination, anIsSent, aDeleteOldDestination), aSource, aNewDestination, anIsSent );
		oldDestination = anOldDestination;
//		newDestination = aNewDestination;
//		source = aSource;
//		isSent = anIsSent;
		deleteOldDestinaton = aDeleteOldDestination;
//		isForwarded = aIsForwarded;
	}
//	@Override
//	public Object getSource() {
//		return source;
//	}
	@Override
	public Object getOldDestination() {
		return oldDestination;
	}
	@Override
	public Object getNewDestination() {
		return getDestination();
	}
//	@Override
//	public boolean isSent() {
//		return isSent;
//	}
//	@Override
//	public boolean isForwarded() {
//		return isForwarded;
//	}
	@Override
	public boolean deleteOldDestination() {
		return deleteOldDestinaton;
	}
	
	public static String computeReplaceConnectionEventMessage (Object aSource,  Object anOldDestination,  Object aNewDestination, boolean anIsSent, boolean aDeleteOldDestination) {
		if (aDeleteOldDestination)
			return "Delete " + AConnectionEvent.isSentToString(anIsSent) + " destination " + anOldDestination + " and connected its source " + aSource + "to new destination " + aNewDestination ;
		else
			return "Replaced destination " + anOldDestination + " of "	+ AConnectionEvent.isSentToString(anIsSent) + 
					" source " 	+ aSource + " with new destination " + aNewDestination;
	
	}

}
