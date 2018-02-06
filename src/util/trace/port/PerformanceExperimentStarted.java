package util.trace.port;

import java.util.List;

import sessionport.datacomm.duplex.object.relayed.MessageWithSource;
import util.annotations.ComponentWidth;
import util.annotations.DisplayToString;
import util.trace.TraceableInfo;
import util.trace.Tracer;
@DisplayToString(true)
@ComponentWidth(1000)
public class PerformanceExperimentStarted extends TraceableInfo {
	

	public PerformanceExperimentStarted (String aMessage, Object aFinder, 
			long aStartTime, int aNumInputs ) {
		super (aMessage, aFinder);	
	}
	
	public static PerformanceExperimentStarted newCase(Object aSource, Object aFinder, 
			long aStartTime, int aNumInputs) {
    	String aMessage = aStartTime + ":" + aNumInputs;
    	PerformanceExperimentStarted retVal = new PerformanceExperimentStarted(aMessage, aFinder, aStartTime, aNumInputs);
    	retVal.announce();
    	return retVal;
	}

}
