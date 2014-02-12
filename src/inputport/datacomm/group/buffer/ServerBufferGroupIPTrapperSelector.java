package inputport.datacomm.group.buffer;

import inputport.datacomm.group.AGroupTrapperSelector;
import inputport.datacomm.group.GroupTrapperSelector;

import java.nio.ByteBuffer;

public class ServerBufferGroupIPTrapperSelector {
	static GroupTrapperSelector<ByteBuffer, ByteBuffer> trapperSelector = new AGroupTrapperSelector();


	public static GroupTrapperSelector<ByteBuffer, ByteBuffer> getTrapperSelector() {
		return trapperSelector;
	}

	public static void setTrapperSelector(
			GroupTrapperSelector<ByteBuffer, ByteBuffer> trapperSelector) {
		ServerBufferGroupIPTrapperSelector.trapperSelector = trapperSelector;
	}

}
