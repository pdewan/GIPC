package extraip;

import java.io.Serializable;

public interface SequencedRPCReturnValue extends Serializable {	
	Serializable getReturnValue();
	int getCallID();
//	void setReturnValue(Serializable newVal);
}
