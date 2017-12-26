package trace.port.consensus;

import consensus.ProposalFeedbackKind;
import inputport.rpc.RemoteCall;
import util.annotations.ComponentWidth;
import util.annotations.DisplayToString;
import util.trace.TraceableInfo;
@DisplayToString(true)
@ComponentWidth(1000)
public class ProposalLearnNotificationSent extends TraceableInfo {
	

	public ProposalLearnNotificationSent(String aMessage, Object aSource, String anObjectName, float aProposalNumber, Object aProposal, ProposalFeedbackKind anAgreement) {
		super(aMessage, aSource );
	}
	
	
	public static ProposalLearnNotificationSent newCase(Object aSource, String anObjectName, float aProposalNumber, Object aProposal, ProposalFeedbackKind anAgreement) {
    	String aMessage =  anObjectName + "," + aProposalNumber + "=" + aProposal + "-->" + anAgreement;
    	ProposalLearnNotificationSent retVal = new ProposalLearnNotificationSent(aMessage, aSource, anObjectName, aProposalNumber, aProposal, anAgreement);
   	    retVal.announce();
    	return retVal;

	}
	
	static {
//		Tracer.setKeywordDisplayStatus(CallReceived.class, true);
	}

}
