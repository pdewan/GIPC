package inputport.datacomm.simplex.buffer;

import java.nio.ByteBuffer;

import inputport.datacomm.ATrapperSelector;
import inputport.datacomm.TrapperSelector;

public class SimplexBufferClientIPTrapperSelector {
	 static TrapperSelector<ByteBuffer, ByteBuffer> 
	 	trapperSelector =  new ATrapperSelector();

	public static TrapperSelector<ByteBuffer, ByteBuffer> getTrapperSelector() {
		return trapperSelector;
	}

	public static void setTrapperSelector(
			TrapperSelector<ByteBuffer, ByteBuffer> trapperSelector) {
		SimplexBufferClientIPTrapperSelector.trapperSelector = trapperSelector;
	}

}
