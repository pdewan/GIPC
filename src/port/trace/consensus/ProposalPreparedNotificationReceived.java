package port.trace.consensus;

import consensus.ProposalFeedbackKind;
import inputport.rpc.RemoteCall;
import util.annotations.ComponentWidth;
import util.annotations.DisplayToString;
import util.trace.TraceableInfo;
@DisplayToString(true)
@ComponentWidth(1000)
public class ProposalPreparedNotificationReceived extends TraceableInfo {
	

	public ProposalPreparedNotificationReceived(String aMessage, Object aSource, 
			String anObjectName,
			float anAcceptedProposalNumber,
			Object anAcceptedProposal,
			float aProposalNumber,
			ProposalFeedbackKind aFeedbackKind) {
		super(aMessage, aSource);
	}
	
	
	public static ProposalPreparedNotificationReceived newCase(Object aSource, 
			String anObjectName, 
			float anAcceptedProposalNumber,
			Object anAcceptedProposal,
			float aProposalNumber,
			ProposalFeedbackKind aFeedbackKind) {
    	String aMessage =  anObjectName + "," + aProposalNumber + "<--(" + anAcceptedProposalNumber + "," + anAcceptedProposal + ") == " + aFeedbackKind;
    	ProposalPreparedNotificationReceived retVal = 
    			new ProposalPreparedNotificationReceived(aMessage, aSource, anObjectName,
    					anAcceptedProposalNumber, anAcceptedProposal, aProposalNumber, aFeedbackKind);
   	    retVal.announce();
    	return retVal;

	}
	
	static {
//		Tracer.setKeywordDisplayStatus(CallReceived.class, true);
	}

}
