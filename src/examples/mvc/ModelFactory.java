package examples.mvc;

public class ModelFactory {
	protected static ObservableCounter observableCounter = new AnObservableCounter();

	public static ObservableCounter getObservableCounter() {
		return observableCounter;
	}

	public static void setObservableCounter(ObservableCounter observableCounter) {
		ModelFactory.observableCounter = observableCounter;
	}
	

}
