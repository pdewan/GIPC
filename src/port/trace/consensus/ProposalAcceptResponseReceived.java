package port.trace.consensus;

import consensus.ProposalFeedbackKind;
import inputport.rpc.RemoteCall;
import util.annotations.ComponentWidth;
import util.annotations.DisplayToString;
import util.trace.TraceableInfo;
@DisplayToString(true)
@ComponentWidth(1000)
public class ProposalAcceptResponseReceived extends TraceableInfo {
	

	public ProposalAcceptResponseReceived(String aMessage, Object aSource, String anObjectName, float aProposalNumber, Object aProposal, ProposalFeedbackKind anAgreement) {
		super(aMessage, aSource );
	}
	
	
	public static ProposalAcceptResponseReceived newCase(Object aSource, String anObjectName, float aProposalNumber, Object aProposal, ProposalFeedbackKind anAgreement) {
    	String aMessage =  anObjectName + "," + aProposalNumber + "=" + aProposal + "-->" + anAgreement;
    	ProposalAcceptResponseReceived retVal = new ProposalAcceptResponseReceived(aMessage, aSource, anObjectName, aProposalNumber, aProposal, anAgreement);
   	    retVal.announce();
    	return retVal;

	}
	
	static {
//		Tracer.setKeywordDisplayStatus(CallReceived.class, true);
	}

}
