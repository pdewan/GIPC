package port.trace.consensus;

import inputport.rpc.RemoteCall;
import util.annotations.ComponentWidth;
import util.annotations.DisplayToString;
import util.trace.TraceableInfo;
@DisplayToString(true)
@ComponentWidth(1000)
public class ProposalAcceptRequestReceived extends TraceableInfo {
	

	public ProposalAcceptRequestReceived(String aMessage, Object aSource, String anObjectName, int aProposalNumber, Object aProposal) {
		super(aMessage, aSource );
	}
	
	
	public static ProposalAcceptRequestReceived newCase(Object aSource, String anObjectName, int aProposalNumber, Object aProposal) {
    	String aMessage =  anObjectName + "." + aProposalNumber + "=" + aProposal;
    	ProposalAcceptRequestReceived retVal = new ProposalAcceptRequestReceived(aMessage, aSource, anObjectName, aProposalNumber, aProposal);
   	    retVal.announce();
    	return retVal;

	}
	
	static {
//		Tracer.setKeywordDisplayStatus(CallReceived.class, true);
	}

}
