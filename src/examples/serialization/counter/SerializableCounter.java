package examples.serialization.counter;

import java.io.Serializable;

import examples.rmi.counter.DistributedCounter;

public interface SerializableCounter extends DistributedCounter, Serializable{

}
