package examples.threads.counter;

public class ACounter implements Counter {
	int value;
	public synchronized int getValue() {
		sleep();// forcing thread switch on context switch
		System.out.println("getValue<--" + value);
		return value;
	}
	public synchronized void increment() {
		int temp = value; // simulating load of memory word to register
		sleep();// forcing thread switch on context switch
		temp++; // simulating register manipulation
		value = temp; // simulating storing of register in memory
		System.out.println("increment-->" + value);
	}
	static  final int DELAY = 300;
	void sleep () {
		try {
			Thread.sleep(DELAY);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}	
}
	


