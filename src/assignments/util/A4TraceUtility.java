package assignments.util;



import util.trace.ImplicitKeywordKind;
import util.trace.TraceableInfo;
import util.trace.Tracer;
import util.trace.port.objects.ReceivedMessageDequeued;
import util.trace.port.objects.ReceivedMessageQueueCreated;
import util.trace.port.rpc.ProxyMethodCalled;
import util.trace.port.rpc.ReceivedReturnValueQueued;
import util.trace.port.rpc.RemoteCallReturnValueDetermined;


public class A4TraceUtility {
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
		
	
		Tracer.setKeywordPrintStatus(ProxyMethodCalled.class, true);
		Tracer.setKeywordPrintStatus(ReceivedReturnValueQueued.class, true);
		Tracer.setKeywordPrintStatus(ReceivedMessageDequeued.class, true);
		Tracer.setKeywordPrintStatus(RemoteCallReturnValueDetermined.class, true);		
		Tracer.setKeywordPrintStatus(ReceivedMessageQueueCreated.class, true);
		

		


	}

}
