package inputport.rpc.group;

import inputport.datacomm.group.AGroupTrapperSelector;
import inputport.datacomm.group.GroupTrapperSelector;

public class GroupSerializableCallTrapperSelector {
	 static GroupTrapperSelector<Object, Object> 
	 	trapperSelector =   new AGroupTrapperSelector();;

	public static GroupTrapperSelector<Object, Object> getTrapperSelector() {
		return trapperSelector;
	}

	public static void setTrapperSelector(
			GroupTrapperSelector<Object, Object> trapperSelector) {
		GroupSerializableCallTrapperSelector.trapperSelector = trapperSelector;
	}
	static {
		GroupSerializableCallTrapperSelector.getTrapperSelector().setGroupSendTrapperFactory(new AGroupSerializableCallTrapperFactory());
	}

}
