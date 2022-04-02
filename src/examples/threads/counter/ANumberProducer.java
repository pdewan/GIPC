package examples.threads.counter;

public class ANumberProducer implements NumberProducer {
	Counter counter;
	int numIncrements ;
	public ANumberProducer(Counter aCounter, int aNumIncrements) {
		counter = aCounter;
		numIncrements = aNumIncrements;
	}
	 public  void produceNumbers() {
		  for (int anIndex = 0; anIndex < numIncrements; anIndex++) {
			  counter.increment();
		  }
	  }
	@Override
	public void run() {
		produceNumbers();
	}
}
