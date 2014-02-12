package oldrpcip;

import java.io.Serializable;

public class AnRPCReturnValue implements RPCReturnValue {
	Serializable returnValue;
	public AnRPCReturnValue(Serializable theReturnValue) {
		returnValue = theReturnValue;
	}
	public Serializable getReturnValue() {
		return returnValue;
	}
}
