package examples.threads;

public interface CircularBoundedBuffer<ElementType> {
	void put(ElementType element);
	ElementType get();
}
