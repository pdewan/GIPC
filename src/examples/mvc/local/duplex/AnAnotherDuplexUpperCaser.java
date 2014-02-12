package examples.mvc.local.duplex;

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.HashMap;
import java.util.Map;

import examples.mvc.local.simplex.ASimplexUpperCaser;
import examples.mvc.rmi.collaborative.relaying.Echoer;

public class AnAnotherDuplexUpperCaser extends ASimplexUpperCaser  implements AnotherDuplexUpperCaser{
	protected Counter counter;
	Echoer echoer;

	public AnAnotherDuplexUpperCaser(Counter aCounter) {
		counter = aCounter;
	}
	public AnAnotherDuplexUpperCaser() {
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
	protected void echo (String aString) {
		echoer.echo(aString);	
	}
	
	protected  String computeReplierLabelledUpperCase(String anInput) {
		return "You said:" + super.toUpperCase(anInput);
	}	
//	protected String computeResult(String anInput) {
//		return computeReplierLabelledUpperCase(anInput) + counterFeedback(getCounterValue());
//	}	
	@Override
	public void echoUpperCase(String anInput) {		
//			String echo = computeResult(anInput);
//			echo(echo);
			echo(computeReplierLabelledUpperCase(anInput) + counterFeedback());
	}	
	public void addListener(Echoer anEchoer) {
		echoer = anEchoer;
	}
	
		

}
