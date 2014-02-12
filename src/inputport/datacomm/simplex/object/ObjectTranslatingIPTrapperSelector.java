package inputport.datacomm.simplex.object;

import inputport.datacomm.ATrapperSelector;
import inputport.datacomm.TrapperSelector;

import java.nio.ByteBuffer;


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
