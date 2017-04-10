package port.trace.consensus;

import consensus.ProposalVetoKind;
import inputport.rpc.RemoteCall;
import util.annotations.ComponentWidth;
import util.annotations.DisplayToString;
import util.trace.TraceableInfo;
@DisplayToString(true)
@ComponentWidth(1000)
public class WaitingForSuccessfulProposalMessageReceipt extends TraceableInfo {
	

	public WaitingForSuccessfulProposalMessageReceipt(String aMessage, Object aSource, String anObjectName, float aProposalNumber, Object aProposal, long aDisconnectionTime) {
		super(aMessage, aSource );
	}
	
	
	public static WaitingForSuccessfulProposalMessageReceipt newCase(Object aSource, String anObjectName, float aProposalNumber, Object aProposal, long aDisconnectionTime) {
    	String aMessage =  anObjectName + "," + aProposalNumber + "=" + aProposal + "-->" +  aDisconnectionTime;
    	WaitingForSuccessfulProposalMessageReceipt retVal = new WaitingForSuccessfulProposalMessageReceipt(aMessage, aSource, anObjectName, aProposalNumber, aProposal,  aDisconnectionTime);
   	    retVal.announce();
    	return retVal;

	}
	
	static {
//		Tracer.setKeywordDisplayStatus(CallReceived.class, true);
	}

}
