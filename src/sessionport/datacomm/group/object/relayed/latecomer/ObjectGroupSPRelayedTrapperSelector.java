package sessionport.datacomm.group.object.relayed.latecomer;

import inputport.datacomm.group.AGroupTrapperSelector;
import inputport.datacomm.group.GroupTrapperSelector;


public class ObjectGroupSPRelayedTrapperSelector {
	static GroupTrapperSelector<Object, Object> trapperSelector =  
		new AGroupTrapperSelector();


	public static GroupTrapperSelector<Object, Object> getTrapperSelector() {
		return trapperSelector;
	}

	public static void setTrapperSelector(
			GroupTrapperSelector<Object, Object> trapperSelector) {
		ObjectGroupSPRelayedTrapperSelector.trapperSelector = trapperSelector;
	}
	
//	static {
//		trapperSelector.setGroupSendTrapperFactory(new ASerializingGroupForwarderFactory());
//	
//	
//	}

}
