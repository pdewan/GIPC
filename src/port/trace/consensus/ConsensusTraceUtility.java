package port.trace.consensus;



import port.trace.rpc.CallReceived;
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

		Tracer.setKeywordPrintStatus(ProposalAcceptRequestReceived.class, true);
		Tracer.setKeywordPrintStatus(ProposalAcceptRequestSent.class, true);
		Tracer.setKeywordPrintStatus(ProposalConsensusOccurred.class, true);
		
		Tracer.setKeywordPrintStatus(ProposalLearnedNotificationReceived.class, true);
		Tracer.setKeywordPrintStatus(ProposalLearnedNotificationSent.class, true);
		
		Tracer.setKeywordPrintStatus(ProposalLearnNotificationReceived.class, true);
		Tracer.setKeywordPrintStatus(ProposalLearnNotificationtSent.class, true);
		
		Tracer.setKeywordPrintStatus(ProposalPreparedNotificationReceived.class, true);
		Tracer.setKeywordPrintStatus(ProposalPreparedNotificationSent.class, true);
		
		Tracer.setKeywordPrintStatus(ProposalPrepareNotificationReceived.class, true);
		Tracer.setKeywordPrintStatus(ProposalPrepareNotificationSent.class, true);
		
		Tracer.setKeywordPrintStatus(ProposalStateChanged.class, true);
		Tracer.setKeywordPrintStatus(ProposalWaitEnded.class, true);
		Tracer.setKeywordPrintStatus(ProposalWaitStarted.class, true);


	}

}
