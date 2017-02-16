package inputport.rpc.duplex;

import java.io.Serializable;

public interface RPCReturnValue extends Serializable {	
	Object getReturnValue();
	public boolean isException(); 
//	void setReturnValue(Object newVal);
}
