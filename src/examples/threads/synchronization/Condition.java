package examples.threads.synchronization;

public interface Condition {
	public void condWait();
	public void condSignal();

}
