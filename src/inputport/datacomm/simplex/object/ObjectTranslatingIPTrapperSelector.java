package inputport.datacomm.simplex.object;

import java.nio.ByteBuffer;

import inputport.datacomm.ATrapperSelector;
import inputport.datacomm.TrapperSelector;


public class ObjectTranslatingIPTrapperSelector {
	 static TrapperSelector<Object, ByteBuffer> 
	 	trapperSelector =  new ATrapperSelector();

	public static TrapperSelector<Object, ByteBuffer> getTrapperSelector() {
		return trapperSelector;
	}

	public static void setTrapperSelector(
			TrapperSelector<Object, ByteBuffer> trapperSelector) {
		ObjectTranslatingIPTrapperSelector.trapperSelector = trapperSelector;
	}
	
	static {
		trapperSelector.
			setSendTrapperFactory(new ASerializingForwarderFactory());
		trapperSelector.
		setReceiveTrapperFactory(new ADeserializingForwarderFactory());
	}

}
