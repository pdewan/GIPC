package examples.threads;

import util.trace.Tracer;

public class ACircularBoundedBuffer<ElementType> implements CircularBoundedBuffer<ElementType>{
	public static final int  MAX_SIZE = 4;
	Object[] buffer = new Object[MAX_SIZE];
	int size = 0;
	int nextIn = 0;
	int nextOut = 0;
	
	public synchronized void put(ElementType element) {
		Tracer.info(this, "Size before put:" + size);
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
		Tracer.info(this, "Size after put:" + size);

		notify();
	}	
	public synchronized ElementType get() {
		Tracer.info(this, "Size before get:" + size);
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
		Tracer.info(this, "Size after get:" + size);
		notify();
		return retVal;		
	}
	
}