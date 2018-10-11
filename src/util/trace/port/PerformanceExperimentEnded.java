package util.trace.port;

import util.annotations.ComponentWidth;
import util.annotations.DisplayToString;
import util.trace.TraceableInfo;
@DisplayToString(true)
@ComponentWidth(1000)
public class PerformanceExperimentEnded extends TraceableInfo {
	

	public PerformanceExperimentEnded (String aMessage, Object aFinder, 
			long aStartTime, long anEndTime, long aDuration, int aNumInputs ) {
		super (aMessage, aFinder);	
	}
	
	public static PerformanceExperimentEnded newCase(Object aSource, 
			long aStartTime, long anEndTime, long aDuration, int aNumInputs) {
    	String aMessage = aStartTime + "-" + anEndTime + "=" + aDuration + ":" + aNumInputs;
    	PerformanceExperimentEnded retVal = new PerformanceExperimentEnded(aMessage, aSource, aStartTime, anEndTime, aDuration, aNumInputs);
    	retVal.announce();
    	return retVal;
	}

}
