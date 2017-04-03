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
		
		Tracer.setKeywordPrintStatus(ProposalLearnedNotificationtReceived.class, true);
		Tracer.setKeywordPrintStatus(ProposalLearnedNotificationtSent.class, true);
		Tracer.setKeywordPrintStatus(ProposalLearnNotificationtReceived.class, true);
		Tracer.setKeywordPrintStatus(ProposalLearnNotificationtSent.class, true);
		
		Tracer.setKeywordPrintStatus(ProposalStateChanged.class, true);
		Tracer.setKeywordPrintStatus(ProposalWaitEnded.class, true);
		Tracer.setKeywordPrintStatus(ProposalWaitStarted.class, true);


	}

}
