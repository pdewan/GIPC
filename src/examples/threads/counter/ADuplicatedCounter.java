package examples.threads.counter;

public class ADuplicatedCounter extends ACounter implements DuplicatedCounter {
	DuplicatedCounter peerCounter;	
	@Override
	public synchronized void duplicatedIncrement()  {
		System.out.println(Thread.currentThread() + " about to increment its counter: " + this);
		increment();
		System.out.println(Thread.currentThread() + " about to increment peer counter: " + peerCounter);
		peerCounter.increment();
	}
	@Override
	public  void setPeer(DuplicatedCounter aCounter) {
		peerCounter = aCounter;
	}
}
