package inputport.rpc.duplex.example;


public class AnAnotherCounter implements AnotherCounter {
	int counter;
	@Override
	public int getValue() {
//		System.out.println ("ACounter returning:" + counter);
		return counter;
	}
	@Override
	public void increment(int val) {
		counter += val;
	}
	@Override
	public boolean equals(Object otherObject) {
		if (!(otherObject instanceof AnotherCounter))
				return false;
		
			return getValue() == ((AnotherCounter) otherObject).getValue();
		
	}	
}
