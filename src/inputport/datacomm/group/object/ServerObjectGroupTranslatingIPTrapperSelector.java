package inputport.datacomm.group.object;

import java.nio.ByteBuffer;

import inputport.datacomm.group.AGroupTrapperSelector;
import inputport.datacomm.group.GroupTrapperSelector;


public class ServerObjectGroupTranslatingIPTrapperSelector {
	static GroupTrapperSelector<Object, ByteBuffer> trapperSelector =  
		new AGroupTrapperSelector();


	public static GroupTrapperSelector<Object, ByteBuffer> getTrapperSelector() {
		return trapperSelector;
	}

	public static void setTrapperSelector(
			GroupTrapperSelector<Object, ByteBuffer> trapperSelector) {
		ServerObjectGroupTranslatingIPTrapperSelector.trapperSelector = trapperSelector;
	}
	
	static {
		trapperSelector.setGroupSendTrapperFactory(new ASerializingGroupForwarderFactory());
	
	
	}

}
