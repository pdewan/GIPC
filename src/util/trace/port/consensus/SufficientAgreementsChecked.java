package util.trace.port.consensus;

import util.annotations.ComponentWidth;
import util.annotations.DisplayToString;
import util.trace.TraceableInfo;
@DisplayToString(true)
@ComponentWidth(1000)
public class SufficientAgreementsChecked extends TraceableInfo {
	

	public SufficientAgreementsChecked(String aMessage, Object aSource, String anObjectName, float aProposalNumber, Object aProposal, short aMaxAcceptors, short aCurrentAcceptors, float aRequiredAgreements, int anAcceptNotifications, int anAgreements, Boolean aResult) {
		super(aMessage, aSource );
	}
	
	
	public static SufficientAgreementsChecked newCase(Object aSource, String anObjectName, float aProposalNumber, Object aProposal, short aMaxAcceptors, short aCurrentAcceptors, float aRequiredAgreements, int anAcceptNotifications, int anAgreements, Boolean aResult) {
    	String aMessage =  anObjectName + "," + aProposalNumber + "," + aProposal + ":" + anAgreements + "|" + anAcceptNotifications + "|" + aCurrentAcceptors + "|" + aMaxAcceptors + "?" + aRequiredAgreements + "-->" + aResult;
    	SufficientAgreementsChecked retVal = new SufficientAgreementsChecked(aMessage, aSource, anObjectName, aProposalNumber, aProposal, aMaxAcceptors, aCurrentAcceptors, aRequiredAgreements, anAcceptNotifications, anAgreements, aResult);
   	    retVal.announce();
    	return retVal;

	}
	
	static {
//		Tracer.setKeywordDisplayStatus(CallReceived.class, true);
	}

}
