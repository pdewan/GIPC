package inputport.datacomm.group.buffer;

import inputport.datacomm.group.GroupToUniSendTrapperSelector;

import java.nio.ByteBuffer;

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
