package serialization.examples;

public class AnIntHistory implements IntHistory {
	public final int MAX_SIZE = 50;
	int[] contents = new int[MAX_SIZE];
	int size = 0;
	public int size() {
		return size;
	}	
	public int get (int index) {
		return contents[index];
	}
	boolean isFull() {
		return size == MAX_SIZE;
	}
	public void add(int element) {
		if (isFull())
			System.out.println("Adding item to a full history");
		else {
			contents[size] = element;
			size++;
		}
	} 
	
	
}
