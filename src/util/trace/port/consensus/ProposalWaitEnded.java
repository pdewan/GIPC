package util.trace.port.consensus;

import consensus.ProposalState;
import inputport.rpc.RemoteCall;
import util.annotations.ComponentWidth;
import util.annotations.DisplayToString;
import util.trace.TraceableInfo;
@DisplayToString(true)
@ComponentWidth(1000)
public class ProposalWaitEnded extends TraceableInfo {
	

	public ProposalWaitEnded(String aMessage, Object aSource, String anObjectName, float aProposalNumber, Object aProposal, ProposalState aProposalState) {
		super(aMessage, aSource );
	}
	
	
	public static ProposalWaitEnded newCase(Object aSource, String anObjectName, float aProposalNumber, Object aProposal, ProposalState aProposalState) {
    	String aMessage =  anObjectName + "," + aProposalNumber + "=" + aProposal + "-->" + aProposalState;
    	ProposalWaitEnded retVal = new ProposalWaitEnded(aMessage, aSource, anObjectName, aProposalNumber, aProposal, aProposalState);
   	    retVal.announce();
    	return retVal;

	}
	
	static {
//		Tracer.setKeywordDisplayStatus(CallReceived.class, true);
	}

}
