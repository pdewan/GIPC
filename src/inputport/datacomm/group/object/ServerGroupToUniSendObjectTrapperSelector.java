package inputport.datacomm.group.object;

import inputport.datacomm.group.AGroupToUniSendTrapperSelector;
import inputport.datacomm.group.GroupToUniSendTrapperSelector;

public class ServerGroupToUniSendObjectTrapperSelector {
	static GroupToUniSendTrapperSelector<Object, Object> trapperSelector = new AGroupToUniSendTrapperSelector<Object, Object>();


	public static GroupToUniSendTrapperSelector<Object, Object> getTrapperSelector() {
		return trapperSelector;
	}

	public static void setTrapperSelector(
			GroupToUniSendTrapperSelector<Object, Object> trapperSelector) {
		ServerGroupToUniSendObjectTrapperSelector.trapperSelector = trapperSelector;
	}

}
