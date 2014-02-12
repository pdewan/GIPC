package examples.mvc.local.duplex;


import bus.uigen.ObjectEditor;


public class ACounter implements Counter{
	public ACounter() {
		super();
	}
	Integer value = 0;	
	public Object getValue() {

		return value;
	}	
	public void increment(int val) {
		value += val;
	}	
	@Override
	public boolean equals(Object otherObject) {
		if (!(otherObject instanceof Counter))
				return false;
		
			return getValue() == ((Counter) otherObject).getValue();		
	}
	public String toString() {
		return "Counter:" + value;
	}
	public static void main (String[] args) {
		ObjectEditor.edit(new ACounter());
	}
}
