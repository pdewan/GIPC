package examples.threads.synchronization;

public class ABoundedBuffer<ElementType> implements BoundedBuffer<ElementType>{
	public static final int  MAX_SIZE = 4;
	Object[] buffer = new Object[MAX_SIZE];
	int size = 0;
	int nextIn = 0;
	int nextOut = 0;
	
//	public synchronized void put(ElementType element) {

	public void put(ElementType element) {
//		if (size >= MAX_SIZE)
//			return;
		while (size >= MAX_SIZE) {
			try {
				wait();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		buffer[nextIn] = element;
		nextIn = (nextIn + 1) % MAX_SIZE;
		size++;
		notify();
	}	
//	public synchronized ElementType get() {
	public  ElementType get() {
//		if (size == 0) {
//			return null;
//		}
		while (size == 0) {
			try {
				wait();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		ElementType retVal = (ElementType) buffer[nextOut];
		nextOut = (nextOut + 1) % MAX_SIZE;
		size--;
		notify();
		return retVal;		
	}
	public static void main(String[] args) {
		BoundedBuffer<String> greetings = new ABoundedBuffer();
		Runnable producer1 = new AProducer<String>(greetings, "Hello");
		Runnable producer2 = new AProducer<String>(greetings, "Ca Va");
		(new Thread(producer1)).start();
		(new Thread(producer2)).start();
	}
}