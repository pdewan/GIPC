package extraip;

import inputport.rpc.duplex.MaybeProcessReturnValue;
import inputport.rpc.simplex.SimplexSentCallCompleter;

public interface ReturnerOfFunctionCall extends 
		SimplexSentCallCompleter,
		MaybeProcessReturnValue {
//	Object returnValueOfFunbctionCall(SerializableCall aCall);


}
