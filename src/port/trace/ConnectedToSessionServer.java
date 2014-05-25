package port.trace;

import port.ParticipantChoice;
import util.annotations.DisplayToString;
import util.trace.TraceableInfo;
@DisplayToString(true)
public  class ConnectedToSessionServer  extends TraceableInfo {
	String sessionServerName;
	ParticipantChoice participantChoice;
	public ConnectedToSessionServer(String aMessage, Object aFinder, String aSessionServerName, ParticipantChoice aParticipantChoice)  {
		super(aMessage, aFinder );
		sessionServerName = aSessionServerName;
		participantChoice = aParticipantChoice;
		
	}
	
//	
//	public Object getDestination() {
//		return  getFinder();
//	}
//	
	
	public String getSessionServerName() {
		return sessionServerName;
	}
	
	public ParticipantChoice getParticipantChoice() {
		return participantChoice;
	}
	
	public static ConnectedToSessionServer newCase(Object aFinder, String aSessionServerName, ParticipantChoice aParticipantChoice) {
    	String aMessage = "Server: " +  aSessionServerName + "Participant Choice: " + aParticipantChoice;
    	ConnectedToSessionServer retVal = new ConnectedToSessionServer(aMessage, aFinder, aSessionServerName, aParticipantChoice);
    	retVal.announce();
    	return retVal;
	}
	static {
//		Tracer.setKeywordDisplayStatus(ConnectedToSessionServer.class, true);
	}

}
