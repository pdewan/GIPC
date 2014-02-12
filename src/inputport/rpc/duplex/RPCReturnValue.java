package inputport.rpc.duplex;

import java.io.Serializable;

public interface RPCReturnValue extends Serializable {	
	Object getReturnValue();
//	void setReturnValue(Object newVal);
}
