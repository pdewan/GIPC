package examples.threads.counter;

public class ANumberConsumer implements NumberConsumer  {
	Counter counter;
	int numIncrements ;
	public ANumberConsumer(Counter aCounter, int aNumIncrements) {
		counter = aCounter;
		numIncrements = aNumIncrements;
	}
	 public  void consumeNumbers() {
		  for (int anIndex = 0; anIndex < numIncrements; anIndex++) {
			  counter.getValue();
		  }
	  }
	@Override
	public void run() {
		consumeNumbers();
		
	}
}
