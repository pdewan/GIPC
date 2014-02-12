package serialization;

import inputport.datacomm.simplex.buffer.SendRegistrar;

public class ASerializerPoolFactory implements SerialiazertPoolFactory {
	public Serializer createSerializerPool(SendRegistrar theSendNotifier) {
		return new ASerializerPool(theSendNotifier);
//		return new ANonCopyingBufferSerializationSupport();
	}
}
