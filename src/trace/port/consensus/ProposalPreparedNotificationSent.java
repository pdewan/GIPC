package trace.port.consensus;

import consensus.ProposalFeedbackKind;
import inputport.rpc.RemoteCall;
import util.annotations.ComponentWidth;
import util.annotations.DisplayToString;
import util.trace.TraceableInfo;
@DisplayToString(true)
@ComponentWidth(1000)
public class ProposalPreparedNotificationSent extends TraceableInfo {
	

	public ProposalPreparedNotificationSent(String aMessage, Object aSource, 
			String anObjectName,
			float anAcceptedProposalNumber,
			Object anAcceptedProposal,
			float aProposalNumber,
			ProposalFeedbackKind aFeedbackKind) {
		super(aMessage, aSource);
	}
	
	
	public static ProposalPreparedNotificationSent newCase(Object aSource, 
			String anObjectName, 
			float anAcceptedProposalNumber,
			Object anAcceptedProposal,
			float aProposalNumber,
			ProposalFeedbackKind aFeedbackKind) {
    	String aMessage =  anObjectName + "," + aProposalNumber + "<--(" + anAcceptedProposalNumber + "," + anAcceptedProposal + ") == " + aFeedbackKind;
    	ProposalPreparedNotificationSent retVal = 
    			new ProposalPreparedNotificationSent(aMessage, aSource, anObjectName,
    					anAcceptedProposalNumber, anAcceptedProposal, aProposalNumber, aFeedbackKind);
   	    retVal.announce();
    	return retVal;

	}
	
	static {
//		Tracer.setKeywordDisplayStatus(CallReceived.class, true);
	}

}
