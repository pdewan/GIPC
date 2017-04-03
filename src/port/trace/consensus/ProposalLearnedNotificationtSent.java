package port.trace.consensus;

import inputport.rpc.RemoteCall;
import util.annotations.ComponentWidth;
import util.annotations.DisplayToString;
import util.trace.TraceableInfo;
@DisplayToString(true)
@ComponentWidth(1000)
public class ProposalLearnedNotificationtSent extends TraceableInfo {
	

	public ProposalLearnedNotificationtSent(String aMessage, Object aSource, String anObjectName, double aProposalNumber, Object aProposal) {
		super(aMessage, aSource );
	}
	
	
	public static ProposalLearnedNotificationtSent newCase(Object aSource, String anObjectName, double aProposalNumber, Object aProposal) {
    	String aMessage =  anObjectName + "." + aProposalNumber + "=" + aProposal;
    	ProposalLearnedNotificationtSent retVal = new ProposalLearnedNotificationtSent(aMessage, aSource, anObjectName, aProposalNumber, aProposal);
   	    retVal.announce();
    	return retVal;

	}
	
	static {
//		Tracer.setKeywordDisplayStatus(CallReceived.class, true);
	}

}
