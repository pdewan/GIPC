package port.trace.consensus;

import inputport.rpc.RemoteCall;
import util.annotations.ComponentWidth;
import util.annotations.DisplayToString;
import util.trace.TraceableInfo;
@DisplayToString(true)
@ComponentWidth(1000)
public class ProposalAcceptedNotificationSent extends TraceableInfo {
	

	public ProposalAcceptedNotificationSent(String aMessage, Object aSource, String anObjectName, float aProposalNumber, Object aProposal, boolean anAgreement) {
		super(aMessage, aSource );
	}
	
	
	public static ProposalAcceptedNotificationSent newCase(Object aSource, String anObjectName, float aProposalNumber, Object aProposal, boolean anAgreement) {
    	String aMessage =  anObjectName + "," + aProposalNumber + "=" + aProposal + "-->" + anAgreement;
    	ProposalAcceptedNotificationSent retVal = new ProposalAcceptedNotificationSent(aMessage, aSource, anObjectName, aProposalNumber, aProposal, anAgreement);
   	    retVal.announce();
    	return retVal;

	}
	
	static {
//		Tracer.setKeywordDisplayStatus(CallReceived.class, true);
	}

}
