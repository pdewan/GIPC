package examples.counter;


import bus.uigen.ObjectEditor;
import util.trace.Tracer;


public class ACounter implements Counter{
	public ACounter() {
		super();
	}
	protected Integer value = 0;	
	public Object getValue() {
//		if (value < 0) throw new IllegalStateException();
		Tracer.info(this, "GetValue returning:" + value);

		return value;
	}	
	
	public void increment(int anIncrement) {
		Tracer.info(this, "Increment called with: "+ anIncrement);
		Tracer.info(this, "Num Threads: "+ Thread.getAllStackTraces().size());

		value += anIncrement;
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
