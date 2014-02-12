package serialization;

import inputport.datacomm.simplex.buffer.SendRegistrar;

public interface SerialiazertPoolFactory {
	Serializer createSerializerPool(SendRegistrar theSendNotifier);
}
