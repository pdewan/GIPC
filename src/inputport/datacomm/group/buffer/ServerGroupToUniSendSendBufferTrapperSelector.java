package inputport.datacomm.group.buffer;

import java.nio.ByteBuffer;

import inputport.datacomm.group.GroupToUniSendTrapperSelector;

public class ServerGroupToUniSendSendBufferTrapperSelector {
	static GroupToUniSendTrapperSelector<ByteBuffer, ByteBuffer> trapperSelector = new AGroupToUniSendBufferSelector();


	public static GroupToUniSendTrapperSelector<ByteBuffer, ByteBuffer> getTrapperSelector() {
		return trapperSelector;
	}

	public static void setTrapperSelector(
			GroupToUniSendTrapperSelector<ByteBuffer, ByteBuffer> trapperSelector) {
		ServerGroupToUniSendSendBufferTrapperSelector.trapperSelector = trapperSelector;
	}

}
