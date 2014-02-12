package inputport.rpc.duplex;

import inputport.rpc.AnAbstractReceivedCallInvoker;
import inputport.rpc.SerializableCall;

public class AFunctionReturnValueDeterminer implements FunctionReturnValueDeterminer {

	@Override
	public boolean isFunctionCall(Object aMessage) {
		return aMessage instanceof SerializableCall && 
				
				!AnAbstractReceivedCallInvoker.isProcedure((SerializableCall) aMessage);
	}

	@Override
	public boolean isReturnValue(Object aMessage) {
		return aMessage instanceof RPCReturnValue;
	}

}
