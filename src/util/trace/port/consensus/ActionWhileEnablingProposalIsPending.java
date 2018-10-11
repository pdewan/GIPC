package util.trace.port.consensus;

import util.annotations.ComponentWidth;
import util.annotations.DisplayToString;
import util.trace.TraceableInfo;
@DisplayToString(true)
@ComponentWidth(1000)
public class ActionWhileEnablingProposalIsPending extends TraceableInfo {
	

	public ActionWhileEnablingProposalIsPending(String aMessage, Object aSource, String anObjectName, float aProposalNumber, Object anAction) {
		super(aMessage, aSource );
	}
	
	
	public static ActionWhileEnablingProposalIsPending newCase(Object aSource, String anObjectName, float aProposalNumber, Object anAction) {
    	String aMessage =  anObjectName + "," + aProposalNumber + "=" + anAction;
    	ActionWhileEnablingProposalIsPending retVal = new ActionWhileEnablingProposalIsPending(aMessage, aSource, anObjectName, aProposalNumber, anAction);
   	    retVal.announce();
    	return retVal;

	}
	
	static {
//		Tracer.setKeywordDisplayStatus(CallReceived.class, true);
	}

}
