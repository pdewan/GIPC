package examples.serialization.counter;

import java.rmi.RemoteException;

import examples.mvc.local.duplex.ACounter;
import examples.mvc.local.duplex.Counter;
import examples.rmi.counter.ADistributedCounter;
import examples.rmi.counter.DistributedCounter;

public class ASerializableDelegatingCounter implements SerializableCounter{
	Counter counter;
	public ASerializableDelegatingCounter(Counter aCounter) {
		counter = aCounter;
	}	
	public void increment(int val)  {
		counter.increment(val);
	}
	public Object getValue()  {
		return counter.getValue();
	}	
}
