package examples.mvc.local.duplex;


import bus.uigen.ObjectEditor;


public class ACounter implements Counter{
	public ACounter() {
		super();
	}
	Integer value = 0;	
	public Object getValue() {
		if (value < 0) throw new IllegalStateException();
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
		return getClass().getSimpleName() + "@" + Integer.toHexString(hashCode()) + ":" + value;
	}
	public static void main (String[] args) {
		ObjectEditor.edit(new ACounter());
	}
}
