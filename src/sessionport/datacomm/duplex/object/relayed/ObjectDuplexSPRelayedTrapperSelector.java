package sessionport.datacomm.duplex.object.relayed;

import inputport.datacomm.ATrapperSelector;
import inputport.datacomm.TrapperSelector;

public class ObjectDuplexSPRelayedTrapperSelector {
	 static TrapperSelector<Object, Object> 
	 	trapperSelector =  new ATrapperSelector();

	public static TrapperSelector<Object, Object> getTrapperSelector() {
		return trapperSelector;
	}

	public static void setTrapperSelector(
			TrapperSelector<Object, Object> trapperSelector) {
		ObjectDuplexSPRelayedTrapperSelector.trapperSelector = trapperSelector;
	}

}
