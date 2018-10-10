package util.trace.port.consensus;

import consensus.ProposalFeedbackKind;
import util.annotations.ComponentWidth;
import util.annotations.DisplayToString;
import util.trace.port.rpc.RemoteCallbackInfo;
@DisplayToString(true)
@ComponentWidth(1000)
public class ProposalAcceptedNotificationReceived extends RemoteCallbackInfo {
	

	public ProposalAcceptedNotificationReceived(String aMessage, Object aSource, String anObjectName, float aProposalNumber, Object aProposal, ProposalFeedbackKind anAgreement) {
		super(aMessage, aSource );
	}
	
	
	public static ProposalAcceptedNotificationReceived newCase(Object aSource, String anObjectName, float aProposalNumber, Object aProposal, ProposalFeedbackKind anAgreement) {
    	String aMessage =  callerPrefix() + anObjectName + "," + aProposalNumber + "=" + aProposal + ":" + anAgreement;
    	ProposalAcceptedNotificationReceived retVal = new ProposalAcceptedNotificationReceived(aMessage, aSource, anObjectName, aProposalNumber, aProposal, anAgreement);
   	    retVal.announce();
    	return retVal;

	}
	
	static {
//		Tracer.setKeywordDisplayStatus(CallReceived.class, true);
	}

}
