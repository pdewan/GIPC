package examples.rmi.counter;

import java.rmi.RemoteException;

import examples.counter.ACounter;
import examples.mvc.counter.AnObservableCounter;


public class ADistributedCounter extends ACounter implements DistributedCounter{
	@Override
	public boolean equals(Object otherObject) {
		if (!(otherObject instanceof DistributedCounter))
				return super.equals(otherObject);
		try {
			return getValue().equals(((DistributedCounter) otherObject).getValue());
//		} catch (RemoteException e) {
		} catch (Exception e) {

			e.printStackTrace();
			return false;
		}
	}	
//	void test() {
//		ADistributedCounter aCounter = new ADistributedCounter();
//		aCounter.increment(2);
//		aCounter.foo();
//	}
	
}
