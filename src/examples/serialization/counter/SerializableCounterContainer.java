package examples.serialization.counter;

import java.io.Serializable;
import java.rmi.Remote;

import examples.mvc.local.duplex.Counter;
import examples.rmi.counter.DistributedCounter;

public interface SerializableCounterContainer extends Serializable, Remote{
	DistributedCounter getCounter();
}
