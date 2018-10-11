package util.trace.port.consensus;

import util.annotations.ComponentWidth;
import util.annotations.DisplayToString;
import util.trace.port.rpc.RemoteCallbackInfo;
@DisplayToString(true)
@ComponentWidth(1000)
public class ProposalLearnedNotificationReceived extends RemoteCallbackInfo {
	

	public ProposalLearnedNotificationReceived(String aMessage, Object aSource, String anObjectName, float aProposalNumber, Object aProposal) {
		super(aMessage, aSource );
	}
	
	
	public static ProposalLearnedNotificationReceived newCase(Object aSource, String anObjectName, float aProposalNumber, Object aProposal) {
    	String aMessage =  callerPrefix() + anObjectName + "," + aProposalNumber + "=" + aProposal;
    	ProposalLearnedNotificationReceived retVal = new ProposalLearnedNotificationReceived(aMessage, aSource, anObjectName, aProposalNumber, aProposal);
   	    retVal.announce();
    	return retVal;

	}
	
	static {
//		Tracer.setKeywordDisplayStatus(CallReceived.class, true);
	}

}
