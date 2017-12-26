package trace.port.consensus;

import inputport.rpc.RemoteCall;
import util.annotations.ComponentWidth;
import util.annotations.DisplayToString;
import util.trace.TraceableInfo;
@DisplayToString(true)
@ComponentWidth(1000)
public class ProposalMade extends TraceableInfo {
	

	public ProposalMade(String aMessage, Object aSource, String anObjectName, float aProposalNumber, Object aProposal) {
		super(aMessage, aSource );
	}
	
	
	public static ProposalMade newCase(Object aSource, String anObjectName, float aProposalNumber, Object aProposal) {
    	String aMessage =  anObjectName + "," + aProposalNumber + "=" + aProposal;
    	ProposalMade retVal = new ProposalMade(aMessage, aSource, anObjectName, aProposalNumber, aProposal);
   	    retVal.announce();
    	return retVal;

	}
	
	static {
//		Tracer.setKeywordDisplayStatus(CallReceived.class, true);
	}

}
