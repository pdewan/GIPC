package inputport.datacomm.simplex.object;

import inputport.datacomm.ATrapperSelector;
import inputport.datacomm.TrapperSelector;

public class ClientObjectTrapperSelector {
	 static TrapperSelector<Object, Object> 
	 	trapperSelector =  new ATrapperSelector();

	public static TrapperSelector<Object, Object> getTrapperSelector() {
		return trapperSelector;
	}

	public static void setTrapperSelector(
			TrapperSelector<Object, Object> trapperSelector) {
		ClientObjectTrapperSelector.trapperSelector = trapperSelector;
	}

}
