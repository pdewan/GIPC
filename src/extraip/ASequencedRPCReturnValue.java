package extraip;

import java.io.Serializable;

import inputport.rpc.duplex.RPCReturnValue;

public class ASequencedRPCReturnValue implements RPCReturnValue {
	Serializable returnValue;
	int callId;
	public ASequencedRPCReturnValue(Serializable theReturnValue, int aCallId) {
		returnValue = theReturnValue;
		callId = aCallId;
	}
	public ASequencedRPCReturnValue() {	}
	public Object getReturnValue() {
		return returnValue;
	}
	@Override
	public String toString() {
		return super.toString() + " " + returnValue;
	}	
	public void setReturnValue(Serializable newVal) {
		returnValue = newVal;
	}
	public int getCallID() {
		return callId;
	}
	public void setCallID(int newVal) {
		callId = newVal;
	}
	@Override
	public boolean isException() {
		return false;
	}
}
