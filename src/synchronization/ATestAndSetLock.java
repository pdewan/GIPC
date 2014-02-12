package synchronization;

public class ATestAndSetLock implements Lock{
	BooleanObject booleanObject;
	public void lock() {
		while (TestAndSetHardware.testAndSet(booleanObject)) {
			;
		}
	}
	public void unlock() {
		booleanObject.set(false);
	}
}
