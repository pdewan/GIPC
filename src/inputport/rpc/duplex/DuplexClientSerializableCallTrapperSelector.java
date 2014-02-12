package inputport.rpc.duplex;

import inputport.datacomm.ATrapperSelector;
import inputport.datacomm.TrapperSelector;

public class DuplexClientSerializableCallTrapperSelector {
	 static TrapperSelector<Object, Object> 
	 	trapperSelector =  new ATrapperSelector();

	public static TrapperSelector<Object, Object> getTrapperSelector() {
		return trapperSelector;
	}

	public static void setTrapperSelector(
			TrapperSelector<Object, Object> trapperSelector) {
		DuplexClientSerializableCallTrapperSelector.trapperSelector = trapperSelector;
	}
	
	static {
//		trapperSelector.
//		setSendTrapperFactory(new ADuplexCallSendTrapperFactory());
////		GlobalState.getSimplexSerializableCallTrapper().
//		trapperSelector.
//		setReceiveTrapperFactory(new ASimplexCallReceiveTrapperFactory());
	}

}
