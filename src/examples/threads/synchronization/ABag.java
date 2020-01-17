package examples.threads.synchronization;

public class ABag<ElementType> implements Bag<ElementType> {
	public static final int  MAX_SIZE = 10;
	Object[] contents = new Object[MAX_SIZE];
	int size = 0;
	public void put(ElementType element) {
		if (size >= MAX_SIZE) {
			System.out.println("Bag is full");
		}
		contents[size] = element;
		size++;
	}
	public void print() {
		for (int index = 0;  index < size; index++) {
			System.out.println(contents[index]);
		}
	}

}
