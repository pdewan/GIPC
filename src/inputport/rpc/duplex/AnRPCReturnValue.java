package inputport.rpc.duplex;


public class AnRPCReturnValue implements RPCReturnValue {
	Object returnValue;
	public AnRPCReturnValue(Object theReturnValue) {
		returnValue = theReturnValue;
	}
	public AnRPCReturnValue() {	}
	public Object getReturnValue() {
		return returnValue;
	}
	@Override
	public String toString() {
		return super.toString() + " " + returnValue;
	}	
	public void setReturnValue(Object newVal) {
		returnValue = newVal;
	}
	
}
