package examples.threads.synchronization;

public interface BoundedBuffer<ElementType> {
	void put(ElementType element);
	ElementType get();
}
