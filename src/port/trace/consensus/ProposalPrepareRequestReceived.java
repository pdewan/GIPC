package port.trace.consensus;

import port.trace.rpc.RemoteCallbackInfo;
import inputport.rpc.RemoteCall;
import util.annotations.ComponentWidth;
import util.annotations.DisplayToString;
@DisplayToString(true)
@ComponentWidth(1000)
public class ProposalPrepareRequestReceived extends RemoteCallbackInfo {
	

	public ProposalPrepareRequestReceived(String aMessage, Object aSource, String anObjectName, float aProposalNumber, Object aProposal) {
		super(aMessage, aSource );
	}
	
	
	public static ProposalPrepareRequestReceived newCase(Object aSource, String anObjectName, float aProposalNumber, Object aProposal) {
    	String aMessage =  callerPrefix() + anObjectName + "," + aProposalNumber + "=" + aProposal;
    	ProposalPrepareRequestReceived retVal = new ProposalPrepareRequestReceived(aMessage, aSource, anObjectName, aProposalNumber, aProposal);
   	    retVal.announce();
    	return retVal;

	}
	
	static {
//		Tracer.setKeywordDisplayStatus(CallReceived.class, true);
	}

}
