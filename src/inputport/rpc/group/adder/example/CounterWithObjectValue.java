package inputport.rpc.group.adder.example;

import inputport.rpc.duplex.example.AnotherCounter;


public interface CounterWithObjectValue extends AnotherCounter {
	Object getObjectValue();
}
