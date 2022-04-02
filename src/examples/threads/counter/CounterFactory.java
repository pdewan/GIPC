package examples.threads.counter;

public class CounterFactory {
//	static Counter counter =  new ACounter();
//	static Counter counter =  new ACoordinatedCounter();
//	static Counter counter =  new ACoordinatedCounterNotifyingAll();
	static Counter counter =  new ACoordinatedCounterNotifyingWait();


	public static Counter  getCounter() {
		return counter;

	}
}
