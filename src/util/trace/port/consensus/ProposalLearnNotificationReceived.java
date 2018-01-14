package util.trace.port.consensus;

import consensus.ProposalFeedbackKind;
import inputport.rpc.RemoteCall;
import util.annotations.ComponentWidth;
import util.annotations.DisplayToString;
import util.trace.TraceableInfo;
import util.trace.port.rpc.RemoteCallbackInfo;
@DisplayToString(true)
@ComponentWidth(1000)
public class ProposalLearnNotificationReceived extends RemoteCallbackInfo {
	

	public ProposalLearnNotificationReceived(String aMessage, Object aSource, String anObjectName, float aProposalNumber, Object aProposal, ProposalFeedbackKind anAgreement) {
		super(aMessage, aSource );
	}
	
	
	public static ProposalLearnNotificationReceived newCase(Object aSource, String anObjectName, float aProposalNumber, Object aProposal, ProposalFeedbackKind anAgreement) {
    	String aMessage = callerPrefix() + anObjectName + "," + aProposalNumber + "=" + aProposal + ":" + anAgreement;
    	ProposalLearnNotificationReceived retVal = new ProposalLearnNotificationReceived(aMessage, aSource, anObjectName, aProposalNumber, aProposal, anAgreement);
   	    retVal.announce();
    	return retVal;

	}
	
	static {
//		Tracer.setKeywordDisplayStatus(CallReceived.class, true);
	}

}
