package port.trace.consensus;

import consensus.ProposalFeedbackKind;
import inputport.rpc.RemoteCall;
import util.annotations.ComponentWidth;
import util.annotations.DisplayToString;
import util.trace.TraceableInfo;
@DisplayToString(true)
@ComponentWidth(1000)
public class ProposalQuorumNotAchieved extends TraceableInfo {
	

	public ProposalQuorumNotAchieved(String aMessage, Object aSource, String anObjectName, float aProposalNumber, Object aProposal, int aTotal, int aVoters) {
		super(aMessage, aSource );
	}
	
	
	public static ProposalQuorumNotAchieved newCase(Object aSource, String anObjectName, float aProposalNumber, Object aProposal, int aTotal, int aVoters) {
    	String aMessage =  anObjectName + "," + aProposalNumber + "=" + aProposal + "-->" +  aVoters + "/" + aTotal;
    	ProposalQuorumNotAchieved retVal = new ProposalQuorumNotAchieved(aMessage, aSource, anObjectName, aProposalNumber, aProposal,  aTotal, aVoters);
   	    retVal.announce();
    	return retVal;

	}
	
	static {
//		Tracer.setKeywordDisplayStatus(CallReceived.class, true);
	}

}
