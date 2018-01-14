package util.trace.port.consensus;

import consensus.ProposalFeedbackKind;
import inputport.rpc.RemoteCall;
import util.annotations.ComponentWidth;
import util.annotations.DisplayToString;
import util.trace.TraceableInfo;
@DisplayToString(true)
@ComponentWidth(1000)
public class WaitedForSuccessfulProposalMessageReceipt extends TraceableInfo {
	

	public WaitedForSuccessfulProposalMessageReceipt(String aMessage, Object aSource, String anObjectName, float aProposalNumber, Object aProposal, long aDisconnectionTime) {
		super(aMessage, aSource );
	}
	
	
	public static WaitedForSuccessfulProposalMessageReceipt newCase(Object aSource, String anObjectName, float aProposalNumber, Object aProposal, long aDisconnectionTime) {
    	String aMessage =  anObjectName + "," + aProposalNumber + "=" + aProposal + "-->" +  aDisconnectionTime;
    	WaitedForSuccessfulProposalMessageReceipt retVal = new WaitedForSuccessfulProposalMessageReceipt(aMessage, aSource, anObjectName, aProposalNumber, aProposal,  aDisconnectionTime);
   	    retVal.announce();
    	return retVal;

	}
	
	static {
//		Tracer.setKeywordDisplayStatus(CallReceived.class, true);
	}

}
