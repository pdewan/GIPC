package port.trace.consensus;

import inputport.rpc.RemoteCall;
import util.annotations.ComponentWidth;
import util.annotations.DisplayToString;
import util.trace.TraceableInfo;
@DisplayToString(true)
@ComponentWidth(1000)
public class ProposalPrepareRequestSent extends TraceableInfo {
	

	public ProposalPrepareRequestSent(String aMessage, Object aSource, String anObjectName, float aProposalNumber, Object aProposal) {
		super(aMessage, aSource );
	}
	
	
	public static ProposalPrepareRequestSent newCase(Object aSource, String anObjectName, float aProposalNumber, Object aProposal) {
    	String aMessage =  anObjectName + "," + aProposalNumber + "=" + aProposal;
    	ProposalPrepareRequestSent retVal = new ProposalPrepareRequestSent(aMessage, aSource, anObjectName, aProposalNumber, aProposal);
   	    retVal.announce();
    	return retVal;

	}
	
	static {
//		Tracer.setKeywordDisplayStatus(CallReceived.class, true);
	}

}
