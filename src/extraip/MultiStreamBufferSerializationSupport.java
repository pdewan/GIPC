package extraip;

import serialization.Serializer;

public interface MultiStreamBufferSerializationSupport {
	Serializer bufferSerializationSupport(String streamName);

}
