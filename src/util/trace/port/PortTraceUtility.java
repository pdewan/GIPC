package util.trace.port;

import util.trace.ImplicitKeywordKind;
import util.trace.TraceableInfo;
import util.trace.Tracer;

public class PortTraceUtility {
		public static void setTracing() {
			Tracer.showInfo(true);
			Tracer.setDisplayThreadName(true); 
			TraceableInfo.setPrintTraceable(true);
			TraceableInfo.setPrintSource(true);
			Tracer.setImplicitPrintKeywordKind(ImplicitKeywordKind.OBJECT_CLASS_NAME);		
			Tracer.setKeywordPrintStatus(PerformanceExperimentStarted.class, true);
			Tracer.setKeywordPrintStatus(PerformanceExperimentEnded.class, true);	
			
		}

}
