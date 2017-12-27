package trace.port.nio;



import util.trace.ImplicitKeywordKind;
import util.trace.TraceableInfo;
import util.trace.Tracer;


public class NIOTraceUtility {
	/**
	 * Do not change this, I will keep updating it and you will run into conflicts
	 * Make a copy if you want this changed
	 */
	public static void setTracing() {
		Tracer.showInfo(true);
		Tracer.setDisplayThreadName(true); 
		TraceableInfo.setPrintTraceable(true);
		TraceableInfo.setPrintSource(true);
		Tracer.setImplicitPrintKeywordKind(ImplicitKeywordKind.OBJECT_CLASS_NAME);		
		Tracer.setKeywordPrintStatus(SocketChannelAccepted.class, true);
		Tracer.setKeywordPrintStatus(SocketChannelBlockingConfigured.class, true);
		Tracer.setKeywordPrintStatus(SocketChannelBound.class, true);
		Tracer.setKeywordPrintStatus(SocketChannelConnectFinished.class, true);
		Tracer.setKeywordPrintStatus(SocketChannelConnectInitiated.class, true);
		Tracer.setKeywordPrintStatus(SocketChannelInterestOp.class, true);
		Tracer.setKeywordPrintStatus(SocketChannelRead.class, true);
		Tracer.setKeywordPrintStatus(SocketChannelRegistered.class, true);
		Tracer.setKeywordPrintStatus(SocketChannelWritten.class, true);


	}

}
