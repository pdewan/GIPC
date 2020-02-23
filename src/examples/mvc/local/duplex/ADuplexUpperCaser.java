package examples.mvc.local.duplex;

import examples.counter.Counter;
import examples.mvc.local.simplex.ASimplexUpperCaser;

public class ADuplexUpperCaser extends ASimplexUpperCaser  implements DuplexUpperCaser{
	protected Counter counter;
	public ADuplexUpperCaser(Counter aCounter) {
		counter = aCounter;
	}
	public ADuplexUpperCaser() {
	}
	@Override
	public String toCountedUpperCase(String anInput) {
			return toUpperCase(anInput) + counterFeedback();
	}
	protected Object getCounterValue() {
		return counter.getValue();
	}
	protected String counterFeedback(Object aCallerCounterValue) {
			return "(" + toStringCounterValue(aCallerCounterValue) + ")";		
	}
	protected String toStringCounterValue(Object aCounterValue) {
		return ""+ aCounterValue;
	}
	protected String counterFeedback() {
		return counterFeedback(getCounterValue());
	}		
}
