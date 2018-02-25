package util.trace.port.consensus;



import util.trace.ImplicitKeywordKind;
import util.trace.TraceableInfo;
import util.trace.Tracer;
import util.trace.port.RemoteEndConnected;
import util.trace.port.RemoteEndDisconnected;
import util.trace.port.buffer.BufferTraceUtility;
import util.trace.port.rpc.CallReceived;
import util.trace.port.rpc.ReceivedCallInitiated;
import util.trace.port.rpc.RemoteCallInitiated;


public class ConsensusTraceUtility {
	
	public static void setTracing() {
		Tracer.showInfo(true);
//		Tracer.setDisplayThreadName(true); 
		TraceableInfo.setPrintTraceable(true);
//		TraceableInfo.setPrintSource(true);
		Tracer.setImplicitPrintKeywordKind(ImplicitKeywordKind.OBJECT_CLASS_NAME);

		Tracer.setKeywordPrintStatus(ProposalAcceptedNotificationReceived.class, true);
		Tracer.setKeywordPrintStatus(ProposalAcceptedNotificationSent.class, true);
		
		Tracer.setKeywordPrintStatus(ProposalAcceptRequestReceived.class, true);
		Tracer.setKeywordPrintStatus(ProposalAcceptRequestSent.class, true);
		
		Tracer.setKeywordPrintStatus(ProposalConsensusOccurred.class, true);
		
		Tracer.setKeywordPrintStatus(ProposalLearnedNotificationReceived.class, true);
		Tracer.setKeywordPrintStatus(ProposalLearnedNotificationSent.class, true);
		
		Tracer.setKeywordPrintStatus(ProposalMade.class, true);
		
		Tracer.setKeywordPrintStatus(ProposalLearnNotificationReceived.class, true);
		Tracer.setKeywordPrintStatus(ProposalLearnNotificationSent.class, true);
		
		Tracer.setKeywordPrintStatus(ProposalPreparedNotificationReceived.class, true);
		Tracer.setKeywordPrintStatus(ProposalPreparedNotificationSent.class, true);
		
		Tracer.setKeywordPrintStatus(ProposalPrepareRequestReceived.class, true);
		Tracer.setKeywordPrintStatus(ProposalPrepareRequestSent.class, true);
		
		Tracer.setKeywordPrintStatus(ProposalStateChanged.class, true);
		Tracer.setKeywordPrintStatus(ProposalWaitEnded.class, true);
		Tracer.setKeywordPrintStatus(ProposalWaitStarted.class, true);
		Tracer.setKeywordPrintStatus(ProposedStateSet.class, true);
		Tracer.setKeywordPrintStatus(RemoteProposeRequestReceived.class, true);

		Tracer.setKeywordPrintStatus(RemoteProposeRequestSent.class, true);
	
		Tracer.setKeywordPrintStatus(SufficientAgreementsChecked.class, true);
		Tracer.setKeywordPrintStatus(WaitedForSuccessfulProposalMessageReceipt.class, true);
		Tracer.setKeywordPrintStatus(WaitingForSuccessfulProposalMessageReceipt.class, true);
		
		Tracer.setKeywordPrintStatus(ReceivedCallInitiated.class, true);
		Tracer.setKeywordPrintStatus(RemoteCallInitiated.class, true);
//		Tracer.setKeywordPrintStatus(RemoteEndConnected.class, true);
//		Tracer.setKeywordPrintStatus(RemoteEndDisconnected.class, true);
		


		



	}

}
