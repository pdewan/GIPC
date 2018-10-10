package util.trace.port.rpc.gipc;



import util.trace.ImplicitKeywordKind;
import util.trace.TraceableInfo;
import util.trace.Tracer;


public class GIPCRPCTraceUtility {
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
		Tracer.setKeywordPrintStatus(GIPCRegistryCreated.class, true);
		Tracer.setKeywordPrintStatus(GIPCRegistryLocated.class, true);
		Tracer.setKeywordPrintStatus(GIPCObjectRegistered.class, true);
		Tracer.setKeywordPrintStatus(GIPCObjectLookedUp.class, true);	

	}

}
