package examples.mvc.local.duplex;


import bus.uigen.ObjectEditor;
import util.trace.Tracer;


public class ACounter implements Counter{
	public ACounter() {
		super();
	}
	protected Integer value = 0;	
	public Object getValue() {
		Tracer.info(this, "getValue called (locally or remotely)");
//		if (value < 0) throw new IllegalStateException();
		Tracer.info(this, "getValue returns:" + value);

		return value;
	}	
	
	public void increment(int val) {
		Tracer.info(this, "Increment called (locally or remotely):" + val);
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
