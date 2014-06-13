package inputport.rpc.group.example;

import inputport.rpc.duplex.example.AnAnotherCounter;

public class ACounterWithObjectValue extends AnAnotherCounter implements CounterWithObjectValue {
	@Override
	public Object getObjectValue() {
		return getValue();
	}
	
}
