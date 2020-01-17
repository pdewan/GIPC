package examples.mvc;

import bus.uigen.ObjectEditor;
import util.trace.Tracer;

public class CounterDriver {
	protected static void setTracing() {
		Tracer.showInfo(true);
		Tracer.setKeywordPrintStatus(CounterDriver.class, true);
		Tracer.setDisplayThreadName(true);
	}
	protected static void composeMVC() {
		ObservableCounter anObservableCounter = ModelFactory.getObservableCounter();
		CounterController aCounterController = new ACounterController(anObservableCounter);
		anObservableCounter.addPropertyChangeListener(new ACounterView());
		ObjectEditor.edit(anObservableCounter);
		aCounterController.processInput();
	}
	public static void main (String[] args) {
		setTracing();
		composeMVC();
	
	}

}
