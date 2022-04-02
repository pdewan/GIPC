package examples.threads.counter;

public interface DuplicatedCounter extends Counter{
	void duplicatedIncrement();
	void setPeer(DuplicatedCounter aCounter);

}
