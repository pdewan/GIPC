package port.trace.consensus;

import inputport.rpc.RemoteCall;
import util.annotations.ComponentWidth;
import util.annotations.DisplayToString;
import util.trace.TraceableInfo;
@DisplayToString(true)
@ComponentWidth(1000)
public class ProposalReceived extends TraceableInfo {
	

	public ProposalReceived(String aMessage, Object aSource, String anObjectName, int aProposalNumber, Object aProposal) {
		super(aMessage, aSource );
	}
	
	
	public static ProposalReceived newCase(Object aSource, String anObjectName, int aProposalNumber, Object aProposal) {
    	String aMessage =  anObjectName+"."+aProposalNumber + "=" + aProposal;
    	ProposalReceived retVal = new ProposalReceived(aMessage, aSource,anObjectName, aProposalNumber, aProposal);
   	    retVal.announce();
    	return retVal;

	}
	
	static {
//		Tracer.setKeywordDisplayStatus(CallReceived.class, true);
	}

}