package serialization.efficient;

import serialization.Serializer;
import serialization.SerializerFactory;

public class ASingleStreamSerializerFactory implements SerializerFactory {
	public Serializer createSerializer() {
//		return new ABufferSerializationSupport();
		return new ASingleStreamSerializer();
	}
}
