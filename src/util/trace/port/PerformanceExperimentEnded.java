package util.trace.port;

import java.util.List;

import sessionport.datacomm.duplex.object.relayed.MessageWithSource;
import util.annotations.ComponentWidth;
import util.annotations.DisplayToString;
import util.trace.TraceableInfo;
import util.trace.Tracer;
@DisplayToString(true)
@ComponentWidth(1000)
public class PerformanceExperimentEnded extends TraceableInfo {
	

	public PerformanceExperimentEnded (String aMessage, Object aFinder, 
			long aStartTime, long anEndTime, long aDuration, int aNumInputs ) {
		super (aMessage, aFinder);	
	}
	
	public static PerformanceExperimentEnded newCase(Object aSource, Object aFinder, 
			long aStartTime, long anEndTime, long aDuration, int aNumInputs) {
    	String aMessage = aStartTime + "-" + anEndTime + "=" + aDuration + ":" + aNumInputs;
    	PerformanceExperimentEnded retVal = new PerformanceExperimentEnded(aMessage, aFinder, aStartTime, anEndTime, aDuration, aNumInputs);
    	retVal.announce();
    	return retVal;
	}

}
