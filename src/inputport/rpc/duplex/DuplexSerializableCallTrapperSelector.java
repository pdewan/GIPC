package inputport.rpc.duplex;

import inputport.datacomm.ATrapperSelector;
import inputport.datacomm.TrapperSelector;

public class DuplexSerializableCallTrapperSelector {
	static TrapperSelector<Object, Object> trapperSelector = new ATrapperSelector();

	public static TrapperSelector<Object, Object> getTrapperSelector() {
		return trapperSelector;
	}

	public static void setTrapperSelector(
			TrapperSelector<Object, Object> trapperSelector) {
		DuplexSerializableCallTrapperSelector.trapperSelector = trapperSelector;
	}

	static {
		// trapperSelector.
		// setSendTrapperFactory(new ADuplexCallSendTrapperFactory());
		// // GlobalState.getSimplexSerializableCallTrapper().
		// trapperSelector.
		// setReceiveTrapperFactory(new ASimplexCallReceiveTrapperFactory());
	}

}
