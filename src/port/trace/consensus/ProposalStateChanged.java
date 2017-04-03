package port.trace.consensus;

import consensus.ProposalState;
import inputport.rpc.RemoteCall;
import util.annotations.ComponentWidth;
import util.annotations.DisplayToString;
import util.trace.TraceableInfo;
@DisplayToString(true)
@ComponentWidth(1000)
public class ProposalStateChanged extends TraceableInfo {
	

	public ProposalStateChanged(String aMessage, Object aSource, String anObjectName, float aProposalNumber, Object aProposal, ProposalState aProposalState) {
		super(aMessage, aSource );
	}
	
	
	public static ProposalStateChanged newCase(Object aSource, String anObjectName, float aProposalNumber, Object aProposal, ProposalState aProposalState) {
    	String aMessage =  anObjectName + "," + aProposalNumber + "=" + aProposal + "-->" + aProposalState;
    	ProposalStateChanged retVal = new ProposalStateChanged(aMessage, aSource, anObjectName, aProposalNumber, aProposal, aProposalState);
   	    retVal.announce();
    	return retVal;

	}
	
	static {
//		Tracer.setKeywordDisplayStatus(CallReceived.class, true);
	}

}
