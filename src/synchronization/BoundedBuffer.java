package synchronization;

public interface BoundedBuffer<ElementType> {
	void put(ElementType element);
	ElementType get();
}
