package synchronization;
public class ABooleanObject implements BooleanObject{
	boolean value;	
	@Override
	public boolean get() {
		return value;
	}
	@Override
	public void set(boolean newVal) {
		value = newVal;
	}
}
