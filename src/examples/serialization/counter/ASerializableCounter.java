package examples.serialization.counter;

import java.io.Serializable;

import examples.rmi.counter.ADistributedCounter;

public class ASerializableCounter extends ADistributedCounter  implements Serializable{

}
