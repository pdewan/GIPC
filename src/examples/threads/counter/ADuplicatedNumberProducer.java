package examples.threads.counter;

public class ADuplicatedNumberProducer implements NumberProducer {
	DuplicatedCounter counter;
	int numIncrements ;
	public ADuplicatedNumberProducer(DuplicatedCounter aCounter, int aNumIncrements) {
		counter = aCounter;
		numIncrements = aNumIncrements;
	}
	 public  void produceNumbers() {
		  for (int anIndex = 0; anIndex < numIncrements; anIndex++) {
			  counter.duplicatedIncrement();
		  }
	  }
	@Override
	public void run() {
		produceNumbers();
	}
}
