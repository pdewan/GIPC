package port.trace.consensus;



import port.trace.RemoteEndConnected;
import port.trace.RemoteEndDisconnected;
import port.trace.buffer.BufferTraceUtility;
import port.trace.rpc.CallReceived;
import port.trace.rpc.ReceivedCallInitiated;
import port.trace.rpc.RemoteCallInitiated;
import util.trace.ImplicitKeywordKind;
import util.trace.TraceableInfo;
import util.trace.Tracer;


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
		Tracer.setKeywordPrintStatus(RemoteProposeRequestReceived.class, true);

		Tracer.setKeywordPrintStatus(RemoteProposeRequestSent.class, true);
	
		Tracer.setKeywordPrintStatus(SufficientAgreementsChecked.class, true);
		Tracer.setKeywordPrintStatus(WaitedForSuccessfulProposalMessageReceipt.class, true);
		Tracer.setKeywordPrintStatus(WaitingForSuccessfulProposalMessageReceipt.class, true);
		
//		Tracer.setKeywordPrintStatus(ReceivedCallInitiated.class, true);
		Tracer.setKeywordPrintStatus(RemoteCallInitiated.class, true);
//		Tracer.setKeywordPrintStatus(RemoteEndConnected.class, true);
//		Tracer.setKeywordPrintStatus(RemoteEndDisconnected.class, true);
		


		



	}

}
