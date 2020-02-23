package examples.counter;


public interface Counter {
	void increment(int val);
	Object getValue();
	boolean equals(Object otherObject);
}
