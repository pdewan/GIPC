package synchronization;

public class ABoundedBufferMain {
	public static void main(String[] args) {
		BoundedBuffer<String> greetings = new ABoundedBufferWithSemaphore();
		Runnable producer1 = new AProducer<String>(greetings, "Hello");
		Runnable producer2 = new AProducer<String>(greetings, "Ca Va");
		(new Thread(producer1)).start();
		(new Thread(producer2)).start();
	}
}
