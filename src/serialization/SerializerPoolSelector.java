package serialization;

import inputport.datacomm.simplex.buffer.SendRegistrar;

public class SerializerPoolSelector {
	static SerialiazertPoolFactory factory = new ASerializerPoolFactory();
	public static SerialiazertPoolFactory getBufferSerializationSupportPoolFactory() {
		return factory;
	}
	public static void setSerializerPoolFactory(SerialiazertPoolFactory newVal) {
		 factory = newVal;
	}
	public static Serializer createSerializerPool(SendRegistrar theSendNotifier) {
		return factory.createSerializerPool(theSendNotifier);
	}
}
