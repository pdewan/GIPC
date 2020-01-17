package examples.threads.synchronization;

public class ABoundedBufferWithSemaphore<ElementType> implements BoundedBuffer<ElementType>{
	public static final int  MAX_SIZE = 4;
	Object[] buffer = new Object[MAX_SIZE];
	int size = 0;
	int nextIn = 0;
	int nextOut = 0;
	Semaphore nonFull = new ASemaphore(MAX_SIZE);
	Semaphore nonEmpty = new ASemaphore(0);

	public void put(ElementType element) {
		nonFull.semWait();
		if (size >= MAX_SIZE)
			return;
		buffer[nextIn] = element;
		nextIn = (nextIn + 1) % MAX_SIZE;
		size++;
		nonEmpty.semSignal();
	}	
	public  ElementType get() {
		nonEmpty.semWait();
		if (size == 0) {
			return null;
		}
		ElementType retVal = (ElementType) buffer[nextOut];
		nextOut = (nextOut + 1) % MAX_SIZE;
		size--;
		nonFull.semSignal();
		return retVal;		
	}
}