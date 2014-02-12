package synchronization;

public class ASemaphoreLock implements Lock {
	Semaphore semaphore = new ASemaphore(1); // let first process go
	public void lock() {
		semaphore.semWait();
	}
	@Override
	public void unlock() {
		semaphore.semSignal();		
	}
}
