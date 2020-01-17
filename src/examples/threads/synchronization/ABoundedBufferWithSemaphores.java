package examples.threads.synchronization;

public class ABoundedBufferWithSemaphores<ElementType> implements BoundedBuffer<ElementType>{
	public static final int  MAX_SIZE = 4;
	Object[] buffer = new Object[MAX_SIZE];
	int size = 0;
	int nextIn = 0;
	int nextOut = 0;

	public void put(ElementType element) {
		if (size >= MAX_SIZE)
			return;
		buffer[nextIn] = element;
		nextIn = (nextIn + 1) % MAX_SIZE;
		size++;
	}	
	public  ElementType get() {
		if (size == 0) {
			return null;
		}
		ElementType retVal = (ElementType) buffer[nextOut];
		nextOut = (nextOut + 1) % MAX_SIZE;
		size--;
		return retVal;		
	}
}