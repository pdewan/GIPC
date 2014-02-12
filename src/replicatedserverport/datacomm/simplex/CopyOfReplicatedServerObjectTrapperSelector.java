package replicatedserverport.datacomm.simplex;

import inputport.datacomm.ATrapperSelector;
import inputport.datacomm.TrapperSelector;

public class CopyOfReplicatedServerObjectTrapperSelector {
	 static TrapperSelector<Object, Object> 
	 	trapperSelector =  new ATrapperSelector();

	public static TrapperSelector<Object, Object> getTrapperSelector() {
		return trapperSelector;
	}

	public static void setTrapperSelector(
			TrapperSelector<Object, Object> trapperSelector) {
		CopyOfReplicatedServerObjectTrapperSelector.trapperSelector = trapperSelector;
	}

}
