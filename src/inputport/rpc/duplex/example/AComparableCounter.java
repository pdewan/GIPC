package inputport.rpc.duplex.example;


public class AComparableCounter extends AnAnotherCounter implements ComparableCounter {	
	public ComparableCounter greater(ComparableCounter aCounter) {	
		if (getValue() < aCounter.getValue()) {
			return aCounter;
		}
			else return this;		
	}
}
