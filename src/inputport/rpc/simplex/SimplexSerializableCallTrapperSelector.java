package inputport.rpc.simplex;

import inputport.datacomm.ATrapperSelector;
import inputport.datacomm.TrapperSelector;

public class SimplexSerializableCallTrapperSelector {
	 static TrapperSelector<Object, Object> 
	 	trapperSelector =  new ATrapperSelector();

	public static TrapperSelector<Object, Object> getTrapperSelector() {
		return trapperSelector;
	}

	public static void setTrapperSelector(
			TrapperSelector<Object, Object> trapperSelector) {
		SimplexSerializableCallTrapperSelector.trapperSelector = trapperSelector;
	}
	
	static {
		trapperSelector.
		setSendTrapperFactory(new ASimplexCallSendTrapperFactory());
//		GlobalState.getSimplexSerializableCallTrapper().
		trapperSelector.
		setReceiveTrapperFactory(new ASimplexCallReceiveTrapperFactory());
	}

}
