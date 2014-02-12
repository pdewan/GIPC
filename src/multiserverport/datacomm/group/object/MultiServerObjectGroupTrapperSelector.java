package multiserverport.datacomm.group.object;

import inputport.datacomm.group.AGroupTrapperSelector;
import inputport.datacomm.group.GroupTrapperSelector;


public class MultiServerObjectGroupTrapperSelector {
	static GroupTrapperSelector<Object, Object> trapperSelector =  
		new AGroupTrapperSelector();


	public static GroupTrapperSelector<Object, Object> getTrapperSelector() {
		return trapperSelector;
	}

	public static void setTrapperSelector(
			GroupTrapperSelector<Object, Object> trapperSelector) {
		MultiServerObjectGroupTrapperSelector.trapperSelector = trapperSelector;
	}
	
//	static {
//		trapperSelector.setGroupSendTrapperFactory(new ASerializingGroupForwarderFactory());
//	
//	
//	}

}
