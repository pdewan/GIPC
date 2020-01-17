package examples.threads.synchronization;

public class AnUnBoundedBuffer<ElementType> implements BoundedBuffer<ElementType>{
	public static final int  MAX_SIZE = 4;
	Object[] buffer = new Object[MAX_SIZE];
	int size = 0;
	int nextIn = 0;
	int nextOut = 0;
	Object putLock = new Object();
	Object getLock = new Object();

	public void put(ElementType element) {
	
		synchronized(putLock) {
			try {
				putLock.wait();
			} catch (Exception e) {
				
			}
		 buffer[nextIn] = element;
		 nextIn = (nextIn + 1) % MAX_SIZE;
		}
	}	
	public  ElementType get() {
		
		ElementType retVal = (ElementType) buffer[nextOut];
		nextOut = (nextOut + 1) % MAX_SIZE;		
		return retVal;		
	}
	public static void main(String[] args) {
		BoundedBuffer<String> greetings = new AnUnBoundedBuffer<String>();
		Runnable producer1 = new AProducer<String>(greetings, "Hello");
		Runnable producer2 = new AProducer<String>(greetings, "Ca Va");
		(new Thread(producer1)).start();
		(new Thread(producer2)).start();
	}
}