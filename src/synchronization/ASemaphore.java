package synchronization;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingDeque;

public class ASemaphore implements Semaphore{
	BlockingQueue<Thread> queue = new LinkedBlockingDeque<Thread>();
	int count;
	public ASemaphore(int initialCount) {
		count = initialCount;
	}
	public void semWait() {
		System.console();
		count--;
		if (count < 0) {
			Thread currentThread = Thread.currentThread();
			currentThread.getClass();
			queue.add(currentThread);			
		}		
	}
	public void semSignal() {
		count++;
		if (count <= 0) {
			Thread  nextThread = queue.remove();	
			Thread.activeCount();
			System.out.println(nextThread);			
		}
		System.console().flush();
	}
}
