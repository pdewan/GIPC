package inputport.rpc.duplex;


public class AnRPCReturnValue implements RPCReturnValue {
	Object returnValue;
	boolean isException;
	
	public boolean isException() {
		return isException;
	}
	public void setException(boolean newVal) {
		this.isException = newVal;
	}
	public AnRPCReturnValue(Object theReturnValue) {
		returnValue = theReturnValue;
	}
	public AnRPCReturnValue(Object theReturnValue, Boolean anIsException) {
		returnValue = theReturnValue;
		if (anIsException != null) {
			isException = anIsException;
		}

	}
	public AnRPCReturnValue() {	}
	public Object getReturnValue() {
		return returnValue;
	}
	@Override
	public String toString() {
		return getClass().getSimpleName() + "(" + returnValue + ")";
	}	
	public void setReturnValue(Object newVal) {
		returnValue = newVal;
	}
	// no transients to initialize
	public void initSerializedObject() {
		
	}
	
	
}
