package examples.threads.synchronization;

public class AProducer<ElementType>  implements Runnable {
	BoundedBuffer<ElementType> boundedBuffer;
	ElementType element;
	public AProducer(BoundedBuffer<ElementType> aBoundedBuffer, ElementType anElement) {
		boundedBuffer = aBoundedBuffer;
		element = anElement;
	}
	@Override
	public void run() {
		boundedBuffer.put(element);
	}

}
