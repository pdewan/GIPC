package extraip;

import inputport.rpc.duplex.RPCReturnValue;

import java.io.Serializable;

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
}
