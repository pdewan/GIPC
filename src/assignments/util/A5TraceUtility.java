package assignments.util;



import util.trace.ImplicitKeywordKind;
import util.trace.TraceableInfo;
import util.trace.Tracer;
import util.trace.port.objects.SerializerTakenFromPool;


public class A5TraceUtility {
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
		
	
		Tracer.setKeywordPrintStatus(SerializerTakenFromPool.class, true);
		

		


	}

}
