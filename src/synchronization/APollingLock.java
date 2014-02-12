package synchronization;
public class APollingLock implements Lock{
	BooleanObject booleanObject = new ABooleanObject();
	public void lock() {
		System.console();
		while (booleanObject.get()) {
			;
		}
		booleanObject.set(true);
	}	
	public void unlock() {
		booleanObject.set(false);
	}	
}
