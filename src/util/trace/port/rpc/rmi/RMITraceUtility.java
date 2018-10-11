package util.trace.port.rpc.rmi;



import util.trace.ImplicitKeywordKind;
import util.trace.TraceableInfo;
import util.trace.Tracer;


public class RMITraceUtility {
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
		Tracer.setKeywordPrintStatus(RMIRegistryCreated.class, true);
		Tracer.setKeywordPrintStatus(RMIRegistryLocated.class, true);
		Tracer.setKeywordPrintStatus(RMIObjectRegistered.class, true);
		Tracer.setKeywordPrintStatus(RMIObjectLookedUp.class, true);	

	}

}
