package synchronization;

public class ABoundedBufferWithExternalWait<ElementType> implements BoundedBuffer<ElementType>{
	public static final int  MAX_SIZE = 4;
	Object[] buffer = new Object[MAX_SIZE];
	int size = 0;
	int nextIn = 0;
	int nextOut = 0;
	Object lock = new Object();

	public synchronized void  put(ElementType element) {
//		if (size >= MAX_SIZE) {
			try {
				lock.wait();
			} catch (Exception e) {}
//		}
			
		buffer[nextIn] = element;
		nextIn = (nextIn + 1) % MAX_SIZE;
		size++;
		lock.notify();
	}	
	public synchronized  ElementType get() {
		if (size == 0) {
			try {
				lock.wait();
			} catch (Exception e) {}
		}
		ElementType retVal = (ElementType) buffer[nextOut];
		nextOut = (nextOut + 1) % MAX_SIZE;
		size--;
		lock.notify();
		return retVal;		
	}
	public static void main(String[] args) {
		BoundedBuffer<String> greetings = new ABoundedBufferWithExternalWait();
		Runnable producer1 = new AProducer<String>(greetings, "Hello");
		Runnable producer2 = new AProducer<String>(greetings, "Ca Va");
		(new Thread(producer1)).start();
		(new Thread(producer2)).start();
	}
}