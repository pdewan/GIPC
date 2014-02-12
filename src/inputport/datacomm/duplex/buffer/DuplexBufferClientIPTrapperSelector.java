package inputport.datacomm.duplex.buffer;

import inputport.datacomm.ATrapperSelector;
import inputport.datacomm.TrapperSelector;

import java.nio.ByteBuffer;

public class DuplexBufferClientIPTrapperSelector {
	 static TrapperSelector<ByteBuffer, ByteBuffer> 
	 	trapperSelector =  new ATrapperSelector();

	public static TrapperSelector<ByteBuffer, ByteBuffer> getTrapperSelector() {
		return trapperSelector;
	}

	public static void setTrapperSelector(
			TrapperSelector<ByteBuffer, ByteBuffer> trapperSelector) {
		DuplexBufferClientIPTrapperSelector.trapperSelector = trapperSelector;
	}

}
