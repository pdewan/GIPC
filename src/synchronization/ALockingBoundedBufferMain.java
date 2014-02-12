package synchronization;

public class ALockingBoundedBufferMain {
	public static void main(String[] args) {
		BoundedBuffer<String> greetings = new ALockingBoundedBuffer(new APollingLock());
		Runnable producer1 = new AProducer(greetings, "Hello");
		Runnable producer2 = new AProducer(greetings, "Ca Va");
		(new Thread(producer1)).start();
		(new Thread(producer2)).start();
	}
}
