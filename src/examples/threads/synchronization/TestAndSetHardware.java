package examples.threads.synchronization;
public class TestAndSetHardware {
	public static synchronized boolean testAndSet(BooleanObject aBooleanObject) {
		boolean retVal = aBooleanObject.get(); // test
		aBooleanObject.set(true); // set		
		return retVal; // return tested value
	}	
}
