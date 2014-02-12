package synchronization;

public class ABagMain {
	public static void main(String[] args) {
		Bag<String> greetings = new ABag();
		Runnable bagRunnable1 = new ABagRunnable(greetings, "Hello");
		Runnable bagRunnable2 = new ABagRunnable(greetings, "Ca Va");
	}
}
