package examples.threads;

import examples.mvc.CounterDriver;
import examples.mvc.ModelFactory;
import util.trace.Tracer;

public class AsynchronousCounterDriver extends CounterDriver {
	
	public static void main (String[] args) {
		Tracer.setKeywordPrintStatus(AsynchronousCounterDriver.class, true);
		setTracing();		
		ModelFactory.setObservableCounter(new AnAsynchronousCounter());
		composeMVC();
		
	}

}
